package com.solvd.online.store.service;
import com.solvd.online.store.model.ShippingMethod;

public interface IShippingMethodService {
    void saveShippingMethodToDB(ShippingMethod shippingMethod);

    void updateShippingMethodInDB(ShippingMethod shippingMethod);

    ShippingMethod getShippingMethodInDB(int id);

    void deleteShippingMethodFromDB(int id);

}