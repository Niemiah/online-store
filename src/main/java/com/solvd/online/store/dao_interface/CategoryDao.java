package com.solvd.online.store.dao_interface;
import com.solvd.online.store.merchandise.Category;
import java.util.List;

public interface CategoryDao {
    public List<Category> getAllCategories();
    public Category getCategory(int categoryId);
    public void updateCategory(Category category);
    public void deleteCategory(Category category);
}
