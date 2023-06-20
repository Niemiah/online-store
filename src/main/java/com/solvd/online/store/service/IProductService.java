package com.solvd.online.store.service;
import com.solvd.online.store.model.Product;

public interface IProductService {
    void saveProductToDB(Product product);

    void updateProductInDB(Product product);

    Product getProductInDB(int id);

    void deleteProductFromDB(int id);

}