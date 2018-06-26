package cn.hxw.study.entity.impl;

import cn.hxw.study.entity.Dog;

/**
 * @author huangxiaowei
 * @date 2018/6/26 10:14
 * @description
 */
public class TaiDi implements Dog {

    @Override
    public void bark() {
        System.out.println("泰迪 汪汪");
    }
}
