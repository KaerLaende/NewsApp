package com.github.KaerLaende.NewsApp.mapper;

import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {

    /**
     * Сопоставляет объект {@link CategoryDto} в объект {@link Category}
     */
    Category categoryDtoToCategory(CategoryDto categoryDto);

    /**
     * Сопоставляет объект {@link Category} в объект {@link CategoryDto}
     */
    CategoryDto categoryToCategoryDto(Category category);


}
