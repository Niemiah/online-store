package com.solvd.online.store;
import com.solvd.online.store.model.Address;
import com.solvd.online.store.model.Payment;
import com.solvd.online.store.model.Invoice;
import com.solvd.online.store.model.Inventory;
import com.solvd.online.store.model.Review;
import com.solvd.online.store.model.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.sql.Date;

public class JsonMain {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Address address = new Address(1, "123 Main St", "Springfield", "IL", "62701", "USA");
        Payment payment = new Payment(1, 12345, "4111111111111111", Date.valueOf("2023-12-31"));
        Invoice invoice = new Invoice(1, 111, 12345, 1);
        Inventory inventory = new Inventory(1, 999, 100);
        Review review = new Review(1, 999, 12345, 5, "Great product!");

        // Create StoreData object
        JsonData jsonData = new JsonData(address, payment, invoice, inventory, review);

        // Write the objects to a single JSON file
        mapper.writeValue(new File("JsonData.json"), jsonData);
    }
}
