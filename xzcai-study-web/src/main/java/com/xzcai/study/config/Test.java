package com.xzcai.study.config;

import java.io.Serializable;

/**
 * @author: Mr.Wang
 * @create: 2018-10-09
 **/
public  class Test {
    public static void main(String[] args) {
        int[] nums = {5,1,2,3,4};
        Test test=new Test();
        System.out.println( test.search(nums,0,nums.length-1,1));
    }
    private int search(int[] nums,int left,int right,int target){
        if(left>right){
            return -1;
        }
        int mid = (right-left)/2+left;
        if(target==nums[mid]){
            return mid;
        }
        if(target>=nums[left]){
            if(nums[mid]>target){
                return search(nums,left,mid-1,target);
            }else{
                if(nums[mid]<nums[left]){
                    return search(nums,left,mid-1,target);
                }else{
                    return search(nums,mid+1,right,target);
                }
            }
        }else{
            if(nums[mid]<target){
                return search(nums,mid+1,right,target);
            }else{
                if(nums[mid]<nums[left]){
                    return search(nums,left,mid-1,target);
                }else{
                    return search(nums,mid+1,right,target);
                }
            }
        }
    }
}
