package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.merchandise.Category;
import com.solvd.online.store.dao_interface.CategoryDao;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    List<Category> categories;

    public CategoryDaoImpl(){
        categories = new ArrayList<Category>();
        Category category1 = new Category(1,"Electronics");
        Category category2 = new Category(2,"Books");
        categories.add(category1);
        categories.add(category2);
    }

    @Override
    public void deleteCategory(Category category) {
        categories.remove(category.getCategoryId());
        System.out.println("Category: Category Id " + category.getCategoryId() + ", deleted from database");
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Category getCategory(int categoryId) {
        return categories.get(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        categories.get(category.getCategoryId()).setCategoryName(category.getCategoryName());
        System.out.println("Category: Category Id " + category.getCategoryId() + ", updated in the database");
    }
}