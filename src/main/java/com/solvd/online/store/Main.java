package com.solvd.online.store;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.model.*;
import com.solvd.online.store.service.*;
import com.solvd.online.store.service.impl.*;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main (String[] args) {

        // creates new address
        Address address = new Address(100, "test","Test", "Test", "0", "Test");
        IAddressService addressService = new AddressService();
        addressService.saveAddressToDB(address);

        // create new order
        Order order = new Order(100, 100);
        IOrderService orderService = new OrderService();
        orderService.saveOrderToDB(order);

        // create new product
        Product product = new Product(100, "Test Product", "This is a test product", 99.99);
        IProductService productService = new ProductService();
        productService.saveProductToDB(product);

        // create new shipping method
        ShippingMethod shippingMethod = new ShippingMethod(100, "Test Method", 10.00, "3-5 days");
        IShippingMethodService shippingMethodService = new ShippingMethodService();
        shippingMethodService.saveShippingMethodToDB(shippingMethod);

        // queries full order info
        LOGGER.info(orderService.getOrderInDB(100));

        // deleting info
        addressService.deleteAddressFromDB(100);
        orderService.deleteOrderFromDB(100);
        productService.deleteProductFromDB(100);
        shippingMethodService.deleteShippingMethodFromDB(100);

        // queries all info
        LOGGER.info(orderService.getAllOrdersFromDB());
        LOGGER.info(productService.getAllProductsFromDB());
        LOGGER.info(shippingMethodService.getAllShippingMethodsFromDB());
        LOGGER.info(addressService.getAllAddressesFromDB());

    }
}