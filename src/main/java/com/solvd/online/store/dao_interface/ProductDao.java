package com.solvd.online.store.dao_interface;
import com.solvd.online.store.merchandise.Product;
import java.util.List;

public interface ProductDao {
    public List<Product> getAllProducts();
    public Product getProduct(int productId);
    public void updateProduct(Product product);
    public void deleteProduct(Product product);
}