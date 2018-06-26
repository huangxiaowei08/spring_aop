package cn.hxw.study.entity.impl;

import cn.hxw.study.entity.Dog;
import cn.hxw.study.entity.Master;

/**
 * @author huangxiaowei
 * @date 2018/6/26 10:23
 * @description
 */
public class Hostess implements Master {

    private Dog dog;

    @Override
    public void walkDog() {
        dog.bark();
    }

    @Override
    public void shopping() {
        System.out.println("疯狂购物中");
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
