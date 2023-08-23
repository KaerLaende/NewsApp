package com.github.KaerLaende.NewsApp.service.impl;

import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.entity.Category;
import com.github.KaerLaende.NewsApp.exception.CategoryNotFoundException;
import com.github.KaerLaende.NewsApp.mapper.CategoryMapper;
import com.github.KaerLaende.NewsApp.repository.CategoryRepository;
import com.github.KaerLaende.NewsApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;



    @Override
    public List<CategoryDto> findAllCategory(){
        List<Category> list = categoryRepository.findAllCategory();
        log.info("Запрошен лист всех категорий: "+ list.toString());
        List<CategoryDto> listDto = categoryMapper.toCategoryDtoList(list);
        log.info(""+listDto);
        return listDto;
    }

    @Override
    public CategoryDto addCategory(String categoryName){
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryName);
        categoryRepository.saveAndFlush(newCategory);
        return categoryMapper.categoryToCategoryDto(newCategory);
    }

    @Override
    public CategoryDto editCategory(long id, String categoryName){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setCategoryName(categoryName);
            return categoryMapper.categoryToCategoryDto(category);
        }
        else {
            throw new CategoryNotFoundException(id);
        }

    }

    @Override
    public CategoryDto deleteCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        log.info("Удаление категории с id = {}", id);
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);
        categoryRepository.deleteById(id);
        return categoryDto;
    }
@Override
public boolean checkCategoryExists(CategoryDto categoryDto) {
        List<Category> list = categoryRepository.findAllCategory();
        for (Category category : list) {
            if (Objects.equals(category.getId(), categoryDto.getId()) & category.getCategoryName().equals(categoryDto.getCategoryName())) {
                return true;
            }
        }
        return false;
    }


}
