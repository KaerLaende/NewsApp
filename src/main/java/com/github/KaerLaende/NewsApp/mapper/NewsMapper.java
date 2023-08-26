package com.github.KaerLaende.NewsApp.mapper;

import com.github.KaerLaende.NewsApp.DTO.*;
import com.github.KaerLaende.NewsApp.entity.News;
import org.mapstruct.*;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NewsMapper {

    /**
     * Для создании новости.
     * Сопоставляет объект {@link CreateNewsDto} в объект {@link News}
     */
    @Mapping(target = "category", source = "createNewsDto.categoryDto")
    News createNewsDtoToNews(CreateNewsDto createNewsDto);

    /**
     * Для отправки в контроллер изменений в новости
     * Сопоставляет объект {@link EditNewsDto} в объект {@link News}
     */
    EditNewsDto newsToEditNewsDto(News news);



    /**
     * Для запросов к новости.
     * Сопоставляет объект {@link News} в объект {@link NewsDto}
     */
    @Mapping(target = "category", source = "category.categoryName")
    NewsDto newsToNewsDto(News news);


    /**
     * Для списка новостей.
     * Сопоставляет список объектов {@link News} и их количество в объект {@link ResponseWrapperNewsDto}
     */
    ResponseWrapperNewsDto listNewsToNewsDto (int count, List<News> results);



}
