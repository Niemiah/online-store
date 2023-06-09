package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.ICategoryDAO;
import com.solvd.online.store.model.Category;
import com.solvd.online.store.impl.CategoryDAO;
import com.solvd.online.store.service.ICategoryService;


public class CategoryService implements ICategoryService {

    @Override
    public void saveCategoryToDB(Category category) {
        ICategoryDAO categoryDao = new CategoryDAO();
        categoryDao.insert(category);
    }

    @Override
    public void updateCategoryInDB(Category category) {
        ICategoryDAO categoryDao = new CategoryDAO();
        categoryDao.update(category);
    }

    @Override
    public Category getCategoryInDB(int id) {
        ICategoryDAO categoryDao = new CategoryDAO();
        return categoryDao.getById(id);
    }
}
