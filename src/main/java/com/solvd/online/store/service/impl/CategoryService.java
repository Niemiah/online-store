package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.ICategoryDAO;
import com.solvd.online.store.model.Category;
import com.solvd.online.store.dao.impl.CategoryDAO;
import com.solvd.online.store.service.ICategoryService;


public class CategoryService implements ICategoryService {

    @Override
    public void saveCategoryToDB(Category category) {
        ICategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insert(category);
    }

    @Override
    public void updateCategoryInDB(Category category) {
        ICategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.update(category);
    }

    @Override
    public Category getCategoryInDB(int id) {
        ICategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getById(id);
    }
}
