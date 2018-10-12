package com.xzcai.study.controller;

import lombok.Data;

/**
 * @author: Mr.Wang
 * @create: 2018-10-11
 **/
public enum TypeEnum {

    type1("type1","234"), type2("type2","234"), type3("type3","234");
    private String key;
    private String value;
    TypeEnum(String key,String value){
        this.key=key;
        this.value=value;
    }
    public static boolean contains(String type){
        for(TypeEnum typeEnum : TypeEnum.values()){
            if(typeEnum.name().equals(type)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        String type = "type";
        TypeEnum typeEnum;
        if(!TypeEnum.contains(type)){
            typeEnum = TypeEnum.type1;
        }
        else{
            typeEnum = TypeEnum.valueOf(type);
        }
        switch (typeEnum) {
            case type1:
                System.out.println("do type1");
                break;
            case type2:
                System.out.println("do type2");
                break;
            case type3:
                System.out.println("do type3");
                break;

            default:
                break;
        }
    }
}
