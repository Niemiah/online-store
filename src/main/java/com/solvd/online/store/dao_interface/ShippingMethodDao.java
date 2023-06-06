package com.solvd.online.store.dao_interface;
import com.solvd.online.store.shipment.ShippingMethod;
import java.util.List;

public interface ShippingMethodDao {
    public List<ShippingMethod> getAllShippingMethods();
    public ShippingMethod getShippingMethod(int shippingId);
    public void updateShippingMethod(ShippingMethod shippingMethod);
    public void deleteShippingMethod(ShippingMethod shippingMethod);
}
