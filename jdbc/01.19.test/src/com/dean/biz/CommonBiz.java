package com.dean.biz;

public interface CommonBiz<T> {
    int add(T t);//增
    int remove(int id);//删
    int update(T t);//改
    T queryOne(int id);//查
}
