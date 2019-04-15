package com.xzcai.study.common.utils;

import javax.annotation.Resource;

/**
 * @author: created by mifang
 * @create: 2019-01-31
 **/
public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed,Require index>=0 and index<=size");
        if (size == data.length) {
            resize(2*data.length);
        }
        for(int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
    }

    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[0] = data[i];
        }
        data = temp;
    }
}
