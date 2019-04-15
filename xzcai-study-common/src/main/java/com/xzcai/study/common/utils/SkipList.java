package com.xzcai.study.common.utils;

import java.util.Random;

/**
 * @author: created by mifang
 * @create: 2019-01-28
 **/
public class SkipList {
    private class SkipListNode {
        private Integer value;
        private SkipListNode next;
        private SkipListNode down;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public SkipListNode getNext() {
            return next;
        }

        public void setNext(SkipListNode next) {
            this.next = next;
        }

        public SkipListNode getDown() {
            return down;
        }

        public void setDown(SkipListNode down) {
            this.down = down;
        }
    }

    private int level = 0;
    private SkipListNode top = null;

    public SkipList() {
        this(4);
    }

    public SkipList(int level) {
        this.level = level;
        SkipListNode skipListNode = null;
        SkipListNode temp = top;
        SkipListNode tempDown = null;
    }

    private int randomLevel() {
        int k = 1;
        while (new Random().nextInt() % 2 == 0 && k < level) {
            k++;
        }
        return k;
    }

    public SkipListNode find(int value){
        SkipListNode node = top;
        while (true){
            while (node.getNext().getValue()<value){
                node=node.getNext();
            }
            if(node.getDown()==null){
                return node;
            }
            node=node.getDown();
        }
    }

    public boolean delete(int value){
        return false;
    }

}
