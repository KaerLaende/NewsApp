package com.github.KaerLaende.NewsApp.service;

import com.github.KaerLaende.NewsApp.DTO.CreateNewsDto;
import com.github.KaerLaende.NewsApp.DTO.EditNewsDto;
import com.github.KaerLaende.NewsApp.DTO.NewsDto;
import com.github.KaerLaende.NewsApp.DTO.ResponseWrapperNewsDto;

/**
 * Данный класс реализуется сервис для работы с новостями. Содержит необходимые зависимости, такие как NewsRepository
 * и NewsMapper, которые используются для взаимодействия с базой данных и маппинга объектов новостей.
 */

public interface NewsService {
    /**
     * добавляет новость на основе переданного объекта CreateNewsDto и возвращает объект NewsDto добавленной новости.
     */
    NewsDto addNews (CreateNewsDto createNewsDto);

    /**
     * возвращает отфильтрованный список новостей на основе переданных параметров категории, названия и содержания
     * новостей. Результат возвращается в виде объекта ResponseWrapperNewsDto.
     */
    ResponseWrapperNewsDto getFilteredNews(String category, String name, String content);

    /**
     * редактирует новость с указанным идентификатором, изменяя ее название и/или содержание. Возвращает объект
     * EditNewsDto отредактированной новости. Если новость с указанным идентификатором не найдена,
     * выбрасывается исключение NewsNotFoundException.
     */
    EditNewsDto editNews(long id, EditNewsDto editNewsDto);

    /**
     * удаляет новость с указанным идентификатором и возвращает объект NewsDto удаленной новости.
     * Если новость с указанным идентификатором не найдена, выбрасывается исключение NewsNotFoundException .
     */
    NewsDto deleteNews(long id);
}
