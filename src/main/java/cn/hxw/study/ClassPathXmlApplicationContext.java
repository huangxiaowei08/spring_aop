package cn.hxw.study;

import cn.hxw.study.aop.framework.ProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author huangxiaowei
 * @date 2018/6/26 09:42
 * @description
 */
public class ClassPathXmlApplicationContext implements Application {

    private String fileName;

    public ClassPathXmlApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Object getBean(String beanID) {
        System.out.println("传递过来的beanID为" + beanID);
        String currentPath = this.getClass().getResource("").getPath().toString();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        Object obj = null;

        try {
            document = saxReader.read(new File(currentPath + fileName));
            String xpath = "/beans/bean[@id='"+beanID+"']";
            Element beanNode = (Element) document.selectSingleNode(xpath);
            String className = beanNode.attributeValue("class");
            if("cn.hxw.study.aop.framework.ProxyFactoryBean".equals(className)){
                Element interceptorNamesNode =
                        (Element)beanNode.selectSingleNode("property[@name='handlerName']");
                String handlerNameValue = interceptorNamesNode.attributeValue("ref");

                Element targetNode =
                        (Element)beanNode.selectSingleNode("property[@name='target']");
                String targetNameValue = targetNode.attributeValue("ref");

                return forProxyFactoryBean(targetNameValue,handlerNameValue);

            }
            obj = Class.forName(className).newInstance();


            Element propertyNode = (Element) beanNode.selectSingleNode("property");
            if(null != propertyNode){
                // System.out.println("当前bean有属性需要注入");
                String propertyName = propertyNode.attributeValue("name");
                // System.out.println("当前bean注入的属性为"+propertyName);

                // 拼接出注入方法
                String methodName = "set"
                        +(propertyName.substring(0, 1)).toUpperCase()
                        +propertyName.substring(1,propertyName.length());
                // System.out.println("自动调用注入方法为"+methodName);

                String setObjectName = propertyNode.attributeValue("ref");
                // System.out.println("需要注入的对象名为"+setObjectName);

                Object diObject = getBean(setObjectName);
                // System.out.println("注入的对象实例"+diObject);

                Method[] methods = obj.getClass().getMethods();

                for(Method method : methods){
                    if(methodName.equals(method.getName())){
                        method.invoke(obj,diObject);
                    }
                }
            }else {
                System.out.println("当前bean没有属性，无需注入");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("返回的实例"+obj);
        return obj;
    }

    private Object forProxyFactoryBean(String targetNameValue, String handlerNameValue) throws Exception{
        System.out.println("目标对象为"+targetNameValue);

        Object target = getBean(targetNameValue);

        System.out.println("代理对象为"+handlerNameValue);

        InvocationHandler handler = (InvocationHandler)getBean(handlerNameValue);

        return new ProxyFactoryBean(target,handler).getProxyBean();
    }
}
