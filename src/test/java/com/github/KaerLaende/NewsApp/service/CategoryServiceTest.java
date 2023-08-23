package com.github.KaerLaende.NewsApp.service;

import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.entity.Category;
import com.github.KaerLaende.NewsApp.exception.CategoryNotFoundException;
import com.github.KaerLaende.NewsApp.mapper.CategoryMapper;
import com.github.KaerLaende.NewsApp.repository.CategoryRepository;
import com.github.KaerLaende.NewsApp.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private static final Category CATEGORY_1 = new Category();
    private static final CategoryDto CATEGORY_1_DTO = new CategoryDto();
    private static final Long ID = 1L;
    private static final String NAME = "Category 1";

    @BeforeEach
    void setUp() {
        CATEGORY_1.setId(ID);
        CATEGORY_1.setCategoryName(NAME);
        CATEGORY_1_DTO.setId(ID);
        CATEGORY_1_DTO.setCategoryName(NAME);
    }

    @Test
    void testFindAllCategory() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        categories.add(CATEGORY_1);
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(CATEGORY_1_DTO);
        when(categoryRepository.findAllCategory()).thenReturn(categories);
        when(categoryMapper.toCategoryDtoList(categories)).thenReturn(categoryDtoList);

        // Act
        List<CategoryDto> result = categoryService.findAllCategory();

        // Assert
        Assertions.assertEquals(categories.size(), result.size());
        verify(categoryRepository, times(1)).findAllCategory();
        verify(categoryMapper, times(1)).toCategoryDtoList(categories);
    }

    @Test
    void testAddCategory() {
        // Arrange
        CategoryDto expectedCategoryDto = CATEGORY_1_DTO;
        when(categoryRepository.saveAndFlush(any(Category.class))).thenReturn(CATEGORY_1);
        when(categoryMapper.categoryToCategoryDto(any(Category.class))).thenReturn(expectedCategoryDto);

        // Act
        CategoryDto result = categoryService.addCategory(NAME);

        // Assert
        Assertions.assertEquals(expectedCategoryDto, result);
        verify(categoryRepository, times(1)).saveAndFlush(any(Category.class));
        verify(categoryMapper, times(1)).categoryToCategoryDto(any(Category.class));
    }

    @Test
    void testEditCategory() {
        // Arrange
        CategoryDto expectedCategoryDto = CATEGORY_1_DTO;
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(CATEGORY_1));
        when(categoryMapper.categoryToCategoryDto(any(Category.class))).thenReturn(expectedCategoryDto);

        // Act
        CategoryDto result = categoryService.editCategory(ID, NAME);

        // Assert
        Assertions.assertEquals(expectedCategoryDto, result);
        Assertions.assertEquals(NAME, CATEGORY_1.getCategoryName());
        verify(categoryRepository, times(1)).findById(ID);
        verify(categoryMapper, times(1)).categoryToCategoryDto(any(Category.class));
    }

    @Test
    void testEditCategory_ThrowsCategoryNotFoundException() {
        // Arrange
        when(categoryRepository.findById(ID)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.editCategory(ID, NAME));
        verify(categoryRepository, times(1)).findById(ID);
        verify(categoryMapper, never()).categoryToCategoryDto(any(Category.class));
    }

    @Test
    void testDeleteCategory() {
        // Arrange
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(CATEGORY_1));
        when(categoryMapper.categoryToCategoryDto(CATEGORY_1)).thenReturn(CATEGORY_1_DTO);


        // Act
        CategoryDto result = categoryService.deleteCategory(ID);

        // Assert
        Assertions.assertEquals(NAME, result.getCategoryName());
        verify(categoryRepository, times(1)).findById(ID);
        verify(categoryMapper, times(1)).categoryToCategoryDto(CATEGORY_1);
        verify(categoryRepository, times(1)).deleteById(ID);
    }

    @Test
    void testDeleteCategory_ThrowsCategoryNotFoundException() {
        // Arrange
        when(categoryRepository.findById(ID)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(ID));
        verify(categoryRepository, times(1)).findById(ID);
        verify(categoryMapper, never()).categoryToCategoryDto(any(Category.class));
        verify(categoryRepository, never()).deleteById(ID);
    }

    @Test
    void testCheckCategoryExists() {
        // Arrange
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(ID);
        categoryDto.setCategoryName(NAME);
        List<Category> categories = new ArrayList<>();
        categories.add(CATEGORY_1);
        when(categoryRepository.findAllCategory()).thenReturn(categories);

        // Act
        boolean result = categoryService.checkCategoryExists(categoryDto);

        // Assert
        Assertions.assertTrue(result);
        verify(categoryRepository, times(1)).findAllCategory();
    }

    @Test
    void testCheckCategoryExists_CategoryDoesNotExist() {
        // Arrange
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(ID);
        categoryDto.setCategoryName(NAME);
        List<Category> categories = new ArrayList<>();
        when(categoryRepository.findAllCategory()).thenReturn(categories);

        // Act
        boolean result = categoryService.checkCategoryExists(categoryDto);

        // Assert
        Assertions.assertFalse(result);
        verify(categoryRepository, times(1)).findAllCategory();
    }
}