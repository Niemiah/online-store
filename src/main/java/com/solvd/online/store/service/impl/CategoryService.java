package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.ICategoryDAO;
import com.solvd.online.store.dao.impl.CategoryDAO;
import com.solvd.online.store.service.ICategoryService;
import com.solvd.online.store.model.Category;

public class CategoryService implements ICategoryService {

    @Override
    public void saveCategoryToDB(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category object is null.");
        }

        ICategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insert(category);
    }

    @Override
    public void updateCategoryInDB(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category object is null.");
        }

        ICategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.update(category);
    }

    @Override
    public Category getCategoryInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        ICategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getById(id);
    }
}