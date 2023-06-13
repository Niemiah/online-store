package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IProductDAO;
import com.solvd.online.store.impl.ProductDAO;
import com.solvd.online.store.service.IProductService;
import com.solvd.online.store.model.Product;

public class ProductService implements IProductService {

    @Override
    public void saveProductToDB(Product product) {
        IProductDAO productDAO = new ProductDAO();
        productDAO.insert(product);
    }

    @Override
    public void updateProductInDB(Product product) {
        IProductDAO productDAO = new ProductDAO();
        productDAO.update(product);
    }

    @Override
    public Product getProductInDB(int id) {
        IProductDAO productDAO = new ProductDAO();
        return productDAO.getById(id);
    }

    @Override
    public void deleteProductFromDB(int id) {

    }

    @Override
    public String getAllProductsFromDB() {
        return null;
    }
}