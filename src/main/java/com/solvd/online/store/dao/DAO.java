package com.solvd.online.store.dao;
import java.util.List;

public interface DAO<T> {
    void insert(T t);
    void update(T t);
    void deleteById(int id);
    T getById(int id);
}