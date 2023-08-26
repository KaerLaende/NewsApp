package com.github.KaerLaende.NewsApp.mapper;

import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.DTO.CreateCategoryDto;
import com.github.KaerLaende.NewsApp.entity.Category;
import org.mapstruct.*;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;


@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    /**
     * Сопоставляет объект {@link CategoryDto} в объект {@link Category}
     */
    Category createCategoryDtoToCategory(CreateCategoryDto categoryDto);

    /**
     * Сопоставляет объект {@link Category} в объект {@link CategoryDto}
     */
    CategoryDto categoryToCategoryDto(Category category);

    /**
     * Сопоставляет список объектов {@link Category} в список CategoryDto
     */
    List<CategoryDto> toCategoryDtoList(List<Category> categoryList);

}
