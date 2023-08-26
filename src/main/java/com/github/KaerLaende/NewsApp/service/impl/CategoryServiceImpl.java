package com.github.KaerLaende.NewsApp.service.impl;

import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.DTO.CreateCategoryDto;
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
    public CategoryDto addCategory(CreateCategoryDto createCategoryDto) {
        Category newCategory = categoryMapper.createCategoryDtoToCategory(createCategoryDto);
        categoryRepository.saveAndFlush(newCategory);
        return categoryMapper.categoryToCategoryDto(newCategory);
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> list = categoryRepository.findAllByOrderByIdDesc();
        log.trace("Запрошен лист всех категорий: " + list.toString());
        List<CategoryDto> listDto = categoryMapper.toCategoryDtoList(list);
        log.trace("" + listDto);
        return listDto;
    }

    @Override
    public CategoryDto editCategory(long id, CreateCategoryDto createCategoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(createCategoryDto.categoryName());
            return categoryMapper.categoryToCategoryDto(category);
        } else {
            throw new CategoryNotFoundException(id);
        }

    }

    @Override
    public CategoryDto deleteCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        log.trace("Удаление категории с id = {}", id);
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);
        categoryRepository.deleteById(id);
        return categoryDto;
    }

    @Override
    public boolean checkCategoryExists(CategoryDto categoryDto) {
        List<Category> list = categoryRepository.findAllByOrderByIdDesc();
        for (Category category : list) {
            if (Objects.equals(category.getId(), categoryDto.getId()) & category.getCategoryName().equals(categoryDto.getCategoryName())) {
                return true;
            }
        }
        String info = "Введенной категории- " + categoryDto.getCategoryName() + "не найдено в базе";
        log.trace(info);
        throw new CategoryNotFoundException("Такой категории нет, вы можете её добавить самостоятельно");
    }


}
