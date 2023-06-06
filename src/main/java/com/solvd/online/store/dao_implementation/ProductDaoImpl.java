package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.merchandise.Product;
import com.solvd.online.store.dao_interface.ProductDao;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    //list is working as a database
    List<Product> products;

    public ProductDaoImpl(){
        products = new ArrayList<Product>();
        Product product1 = new Product(1,"Product1","Description1",10.0);
        Product product2 = new Product(2,"Product2","Description2",20.0);
        products.add(product1);
        products.add(product2);
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product.getProductId());
        System.out.println("Product: Product Id " + product.getProductId() + ", deleted from database");
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProduct(int productId) {
        return products.get(productId);
    }

    @Override
    public void updateProduct(Product product) {
        products.get(product.getProductId()).setProductName(product.getProductName());
        System.out.println("Product: Product Id " + product.getProductId() + ", updated in the database");
    }
}
