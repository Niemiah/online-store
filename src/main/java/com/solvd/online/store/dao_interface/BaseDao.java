package com.solvd.online.store.dao_interface;

import java.util.List;

public interface BaseDao<T> {
    T create(T t);
    T find(int id);
    List<T> findAll();
    T update(T t);
    void delete(T t);
}