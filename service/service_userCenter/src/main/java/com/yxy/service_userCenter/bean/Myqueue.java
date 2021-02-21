package com.yxy.service_userCenter.bean;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Myqueue implements Queue {
    Object []queue;
    int head,last;

    public Myqueue(int length){
        queue=new Object[length];
        head=0;
        last=0;
    }
    @Override
    public int size() {
        return queue.length;
    }

    @Override
    public boolean isEmpty() {
        if(head==last)
        {
            return true;//true代表是空
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {

        queue[last]=o;
        last++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        //
        Object a=queue[head];
        head++;
        return a;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return queue[head];
    }
}


