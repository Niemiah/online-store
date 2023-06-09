package com.solvd.online.store.impl;
import com.solvd.online.store.model.Address;
import com.solvd.online.store.util.ConnectionPool;
import com.solvd.online.store.dao.DAO

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class AddressDAO implements DAO<Address> {  {
    Connection connection = DriverManager.getConnection(url:"", new Properties());
}

    @Override
    public void insert(Address address) {

    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Address getById(int id) {
        return null;
    }
}