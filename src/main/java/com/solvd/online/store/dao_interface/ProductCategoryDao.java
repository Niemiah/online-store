package com.solvd.online.store.dao_interface;
import com.solvd.online.store.merchandise.ProductCategory;
import java.util.List;

public interface ProductCategoryDao {
    public List<ProductCategory> getAllProductCategories();
    public ProductCategory getProductCategory(int productId, int categoryId);
    public void updateProductCategory(ProductCategory productCategory);
    public void deleteProductCategory(ProductCategory productCategory);
}
