package com.github.KaerLaende.NewsApp.service;
import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.DTO.CreateCategoryDto;

import java.util.List;

/**
 * Данный класс реализуется сервис для работы с категориями новостей.
 * Содержит необходимые зависимости, такие как  CategoryRepository  и  CategoryMapper,
 * которые используются для взаимодействия с базой данных и маппинга объектов категорий.
 */
public interface CategoryService {
    /**
     * добавляет новую категорию с указанным названием и возвращает объект  CategoryDto  новой категории.
     */
    CategoryDto addCategory(CreateCategoryDto createCategoryDto);


    /**
     * возвращает список всех категорий новостей в виде объектов  CategoryDto
     */
    List<CategoryDto> findAllCategory();



    /**
     * редактирует категорию с указанным идентификатором, меняя ее название на указанное, и возвращает объект CategoryDto  отредактированной категории.
     * Если категория с указанным идентификатором не найдена, выбрасывается исключение CategoryNotFoundException.
     */

    CategoryDto editCategory(long id, CreateCategoryDto createCategoryDto);

    /**
     *  удаляет категорию с указанным идентификатором и возвращает объект  CategoryDto  удаленной категории.
     *  Если категория с указанным идентификатором не найдена, выбрасывается исключение  CategoryNotFoundException.
     */
    CategoryDto deleteCategory(long id);

    /**
     * проверяет, существует ли категория с указанным объектом  CategoryDto.
     * Возвращает true, если категория существует, иначе возвращает  false.
     */
    boolean checkCategoryExists(CategoryDto categoryDto);
}
