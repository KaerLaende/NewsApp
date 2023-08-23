package com.github.KaerLaende.NewsApp.service;

import com.github.KaerLaende.NewsApp.DTO.CreateNewsDto;
import com.github.KaerLaende.NewsApp.DTO.EditNewsDto;
import com.github.KaerLaende.NewsApp.DTO.NewsDto;
import com.github.KaerLaende.NewsApp.DTO.ResponseWrapperNewsDto;

public interface NewsService {
    NewsDto addNews (CreateNewsDto createNewsDto);

    ResponseWrapperNewsDto getFilteredNews(String category, String name, String content);

    EditNewsDto editNews(long id, String title, String content);

    NewsDto deleteNews(long id);
}
