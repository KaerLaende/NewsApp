package com.github.KaerLaende.NewsApp.service.impl;

import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.entity.Category;
import com.github.KaerLaende.NewsApp.mapper.CategoryMapper;
import com.github.KaerLaende.NewsApp.repository.CategoryRepository;
import com.github.KaerLaende.NewsApp.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Category> findAllCategory(){
        List<Category> list = categoryRepository.findAllCategory();
        log.info("Запрошен лист всех категорий: "+ list.toString());
        return list;
    }

    public CategoryDto addCategory(CategoryDto categoryName){
        Category newCategory = mapper.categoryDtoToCategory(categoryName);
        categoryRepository.saveAndFlush(newCategory);
        return mapper.categoryToCategoryDto(newCategory);
    }

    public CategoryDto editCategory(int id, String categoryName){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setCategoryName(categoryName);
            return mapper.categoryToCategoryDto(category);
        }

    }



}
