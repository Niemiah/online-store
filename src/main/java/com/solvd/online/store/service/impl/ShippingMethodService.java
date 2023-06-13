package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IShippingMethodDAO;
import com.solvd.online.store.impl.ShippingMethodDAO;
import com.solvd.online.store.service.IShippingMethodService;
import com.solvd.online.store.model.ShippingMethod;

public class ShippingMethodService implements IShippingMethodService {

    @Override
    public void saveShippingMethodToDB(ShippingMethod shippingMethod) {
        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        shippingMethodDAO.insert(shippingMethod);
    }

    @Override
    public void updateShippingMethodInDB(ShippingMethod shippingMethod) {
        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        shippingMethodDAO.update(shippingMethod);
    }

    @Override
    public ShippingMethod getShippingMethodInDB(int id) {
        IShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();
        return shippingMethodDAO.getById(id);
    }

    @Override
    public void deleteShippingMethodFromDB(int id) {

    }

    @Override
    public String getAllShippingMethodsFromDB() {
        return null;
    }
}