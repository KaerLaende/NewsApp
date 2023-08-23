package com.github.KaerLaende.NewsApp.service;
import com.github.KaerLaende.NewsApp.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {


    List<CategoryDto> findAllCategory();

    CategoryDto addCategory(String categoryName);

    CategoryDto editCategory(long id, String categoryName);

    CategoryDto deleteCategory(long id);

    boolean checkCategoryExists(CategoryDto categoryDto);
}
