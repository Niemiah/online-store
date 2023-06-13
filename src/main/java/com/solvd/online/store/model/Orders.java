package com.solvd.online.store.model;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Orders")
public class Orders {
    @XmlElement(name = "Order")
    private List<Order> orders;

    public Orders() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}