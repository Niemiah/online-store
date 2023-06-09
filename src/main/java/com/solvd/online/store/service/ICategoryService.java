package com.solvd.online.store.service;

import com.solvd.online.store.model.Category;

public interface ICategoryService {
    void saveCategoryToDB(Category category);

    void updateCategoryInDB(Category category);

    Category getCategoryInDB(int id);
}
