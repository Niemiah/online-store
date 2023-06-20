package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IProductDAO;
import com.solvd.online.store.dao.impl.ProductDAO;
import com.solvd.online.store.service.IProductService;
import com.solvd.online.store.model.Product;

public class ProductService implements IProductService {

    @Override
    public void saveProductToDB(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product object is null.");
        }

        IProductDAO productDAO = new ProductDAO();
        productDAO.insert(product);
    }

    @Override
    public void updateProductInDB(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product object is null.");
        }

        IProductDAO productDAO = new ProductDAO();
        productDAO.update(product);
    }

    @Override
    public Product getProductInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IProductDAO productDAO = new ProductDAO();
        return productDAO.getById(id);
    }

    @Override
    public void deleteProductFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IProductDAO productDAO = new ProductDAO();
        productDAO.deleteById(id);
    }
}