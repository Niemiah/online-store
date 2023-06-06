package com.solvd.online.store.dao_interface;

import java.util.List;

public interface BaseDao<T, ID> {
    T create(T t);
    T find(ID id);
    List<T> findAll();
    T update(T t);
    void delete(T t);
}