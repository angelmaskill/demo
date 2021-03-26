package com.copy.deep.demo5;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * yong_jiang
 * <p>
 * dozer单例的wrapper.
 * <p>
 * dozer在同一jvm里使用单例即可,无需重复创建.
 * 但Dozer4.0自带的DozerBeanMapperSingletonWrapper必须使用dozerBeanMapping
 * .xml作参数初始化,因此重新实现.
 * <p>
 * 实现PO到VO的深层次复制
 */
public class DozerMapperSingleton {

    private static Mapper instance;

    private DozerMapperSingleton() {
        // shoudn't invoke.
    }

    public static synchronized Mapper getInstance() {
        if (instance == null) {
            instance = new DozerBeanMapper();
        }
        return instance;
    }

    public static void main(String[] args) {
        InfoVO2 info2 = new InfoVO2(1, "myl", 1);// 假设这个对象是有值的
        System.out.println(info2.toString());
        InfoVO info1 = new InfoVO();
        System.out.println(info1.toString());
        DozerMapperSingleton.getInstance().map(info2, info1);
        System.out.println(info1.toString());
    }
}