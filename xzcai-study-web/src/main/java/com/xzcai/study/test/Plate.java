package com.xzcai.study.test;

/**
 * @author: created by mifang
 * @create: 2018-11-07
 **/
public class Plate<T> {
    private T item;
    public Plate(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
}
