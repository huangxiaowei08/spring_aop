package cn.hxw.study.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author huangxiaowei
 * @date 2018/6/26 11:10
 * @description
 */
public class HumanHandler implements InvocationHandler {

    /**
     *  目标不是固定的
     */
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * return 返回是原来目标方法所返回的内容 method 就是要执行的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();

        // 具体的业务逻辑代码
        Object returnValue = method.invoke(target, args);

        after();

        return returnValue;
    }

    // 前置任务
    private void before(){
        System.out.println("[代理执行前置任务]整理着装");
        System.out.println("[代理执行前置任务]带上钥匙");
        System.out.println("");
        System.out.println("[核心业务开始]*****************");
    }

    // 后置任务
    private void after(){
        System.out.println("[核心业务结束]*****************");
        System.out.println("");
        System.out.println("[代理执行后置任务]开门");
        System.out.println("[代理执行后置任务]换鞋");
    }


}
