package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.merchandise.ProductCategory;
import com.solvd.online.store.dao_interface.ProductCategoryDao;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

    List<ProductCategory> productCategories;

    public ProductCategoryDaoImpl(){
        productCategories = new ArrayList<ProductCategory>();
        ProductCategory productCategory1 = new ProductCategory(1, 1);
        ProductCategory productCategory2 = new ProductCategory(2, 2);
        productCategories.add(productCategory1);
        productCategories.add(productCategory2);
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        productCategories.remove(productCategory);
        System.out.println("ProductCategory: Product Id " + productCategory.getProductId() + ", Category Id " + productCategory.getCategoryId() + " deleted from database");
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategories;
    }

    @Override
    public ProductCategory getProductCategory(int productId, int categoryId) {
        for(ProductCategory pc : productCategories){
            if(pc.getProductId() == productId && pc.getCategoryId() == categoryId){
                return pc;
            }
        }
        return null;
    }

    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        // Since we don't have any other data to update, this method can be left blank
    }
}
