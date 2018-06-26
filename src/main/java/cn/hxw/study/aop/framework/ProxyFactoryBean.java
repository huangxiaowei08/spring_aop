package cn.hxw.study.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author huangxiaowei
 * @date 2018/6/26 11:16
 * @description
 * 代理工厂
 */
public class ProxyFactoryBean {

    private Object target;

    private InvocationHandler handler;

    public ProxyFactoryBean() {
    }

    public ProxyFactoryBean(Object target, InvocationHandler handler){
        this.target = target;
        this.handler = handler;
    }

    /**
     * 返回本类的一个实例
     * @return
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public Object getProxyBean() throws IllegalArgumentException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        Object obj = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
        return obj;
    }

}
