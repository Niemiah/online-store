package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IShippingMethodDAO;
import com.solvd.online.store.dao.impl.ShippingMethodDAO;
import com.solvd.online.store.service.IShippingMethodService;
import com.solvd.online.store.model.ShippingMethod;

public class ShippingMethodService implements IShippingMethodService {

    @Override
    public void saveShippingMethodToDB(ShippingMethod shippingMethod) {
        if (shippingMethod == null) {
            throw new IllegalArgumentException("ShippingMethod object is null.");
        }

        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        shippingMethodDAO.insert(shippingMethod);
    }

    @Override
    public void updateShippingMethodInDB(ShippingMethod shippingMethod) {
        if (shippingMethod == null) {
            throw new IllegalArgumentException("ShippingMethod object is null.");
        }

        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        shippingMethodDAO.update(shippingMethod);
    }

    @Override
    public ShippingMethod getShippingMethodInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        return shippingMethodDAO.getById(id);
    }

    @Override
    public void deleteShippingMethodFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        shippingMethodDAO.deleteById(id);
    }
}