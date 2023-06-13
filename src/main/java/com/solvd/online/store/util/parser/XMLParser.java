package com.solvd.online.store.util.parser;
import com.solvd.online.store.model.Addresses;
import com.solvd.online.store.model.Categories;
import com.solvd.online.store.model.Orders;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLParser {
    public static void main(String[] args) {
        try {
            File addressesFile = new File("C:\\Users\\color\\online-store\\src\\main\\resources\\xml\\Address.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Addresses.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Addresses addresses = (Addresses) jaxbUnmarshaller.unmarshal(addressesFile);
            System.out.println(addresses.getAddresses().get(0).getAddress());

            File categoriesFile = new File("C:\\Users\\color\\online-store\\src\\main\\resources\\xml\\Category.xml");
            jaxbContext = JAXBContext.newInstance(Categories.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Categories categories = (Categories) jaxbUnmarshaller.unmarshal(categoriesFile);
            System.out.println(categories.getCategories().get(0).getCategoryName());

            File ordersFile = new File("C:\\Users\\color\\online-store\\src\\main\\resources\\xml\\Order.xml");
            jaxbContext = JAXBContext.newInstance(Orders.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Orders orders = (Orders) jaxbUnmarshaller.unmarshal(ordersFile);
            System.out.println(orders.getOrders().get(0).getOrderId());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
