package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.shipment.ShippingMethod;
import java.util.ArrayList;
import java.util.List;

public class ShippingMethodDaoImpl implements ShippingMethodDao {

    List<ShippingMethod> shippingMethods;

    public ShippingMethodDaoImpl(){
        shippingMethods = new ArrayList<ShippingMethod>();
        ShippingMethod shippingMethod1 = new ShippingMethod(1,"Fast",10.5,"2-3 days");
        ShippingMethod shippingMethod2 = new ShippingMethod(2,"Regular",5,"4-6 days");
        shippingMethods.add(shippingMethod1);
        shippingMethods.add(shippingMethod2);
    }

    @Override
    public void deleteShippingMethod(ShippingMethod shippingMethod) {
        shippingMethods.remove(shippingMethod.getShippingId());
        System.out.println("ShippingMethod: Shipping Id " + shippingMethod.getShippingId() + ", deleted from database");
    }

    @Override
    public List<ShippingMethod> getAllShippingMethods() {
        return shippingMethods;
    }

    @Override
    public ShippingMethod getShippingMethod(int shippingId) {
        return shippingMethods.get(shippingId);
    }

    @Override
    public void updateShippingMethod(ShippingMethod shippingMethod) {
        shippingMethods.get(shippingMethod.getShippingId()).setMethodName(shippingMethod.getMethodName());
        System.out.println("ShippingMethod: Shipping Id " + shippingMethod.getShippingId() + ", updated in the database");
    }
}