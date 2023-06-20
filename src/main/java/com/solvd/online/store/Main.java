package com.solvd.online.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.online.store.model.*;
import com.solvd.online.store.service.*;
import com.solvd.online.store.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String JSON_FILE_PATH = "JsonData.json";
    private static final int TEST_ID = 100;

    public static void main(String[] args) {

        try {
            processDatabaseOperations();
            processJsonOperations();
        } catch (IOException e) {
            LOGGER.error("Error during IO operations", e);
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred", e);
        }
    }

    private static void processDatabaseOperations() {

        // Creates new address
        Address address = new Address(TEST_ID, "test", "Test", "Test", "0", "Test");
        IAddressService addressService = new AddressService();
        addressService.saveAddressToDB(address);

        // Create new order
        Order order = new Order(TEST_ID, TEST_ID);
        IOrderService orderService = new OrderService();
        orderService.saveOrderToDB(order);

        // Create new product
        Product product = new Product(TEST_ID, "Test Product", "This is a test product", new BigDecimal("99.99"));
        IProductService productService = new ProductService();
        productService.saveProductToDB(product);

        // Create new shipping method
        ShippingMethod shippingMethod = new ShippingMethod(TEST_ID, "Test Method", 10.00, "3-5 days");
        IShippingMethodService shippingMethodService = new ShippingMethodService();
        shippingMethodService.saveShippingMethodToDB(shippingMethod);

        // Query full order info
        Order queriedOrder = orderService.getOrderInDB(TEST_ID);
        LOGGER.info(queriedOrder);

        // Deleting info
        addressService.deleteAddressFromDB(TEST_ID);
        orderService.deleteOrderFromDB(TEST_ID);
        productService.deleteProductFromDB(TEST_ID);
        shippingMethodService.deleteShippingMethodFromDB(TEST_ID);
    }

    private static void processJsonOperations() throws IOException {

        Address address = new Address(1, "123 Main St", "Springfield", "IL", "62701", "USA");
        Payment payment = new Payment(1, 12345, "4111111111111111", Date.valueOf("2023-12-31"));
        Invoice invoice = new Invoice(1, 111, 12345, 1);
        Inventory inventory = new Inventory(1, 999, 100);
        Review review = new Review(1, 999, 12345, 5, "Great product!");

        // Create StoreData object
        JsonData jsonData = new JsonData(address, payment, invoice, inventory, review);

        // Write the objects to a single JSON file
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(JSON_FILE_PATH), jsonData);
    }
}