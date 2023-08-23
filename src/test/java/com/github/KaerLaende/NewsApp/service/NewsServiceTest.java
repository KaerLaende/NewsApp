package com.github.KaerLaende.NewsApp.service;


import com.github.KaerLaende.NewsApp.DTO.*;
import com.github.KaerLaende.NewsApp.entity.News;
import com.github.KaerLaende.NewsApp.exception.NewsNotFoundException;
import com.github.KaerLaende.NewsApp.mapper.NewsMapper;
import com.github.KaerLaende.NewsApp.repository.NewsRepository;

import com.github.KaerLaende.NewsApp.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsMapper newsMapper;

    @InjectMocks
    private NewsServiceImpl newsService;

    private News news;
    private NewsDto newsDto;
    private CreateNewsDto createNewsDto;
    private EditNewsDto editNewsDto;

    private CategoryDto categoryDto;
    private Long ID;
    private String TITLE;
    private String CONTENT;

    @BeforeEach
    void setUp() {
        news = new News();
        newsDto = new NewsDto();
        categoryDto = new CategoryDto();
        createNewsDto = new CreateNewsDto(TITLE,CONTENT,categoryDto);
        editNewsDto = new EditNewsDto();
        ID = 1L;
        TITLE = "News 1";
        CONTENT = "Content 1";

        news.setId(ID);
        news.setTitle(TITLE);
        news.setContent(CONTENT);
        newsDto.setId(ID);
        newsDto.setTitle(TITLE);
        newsDto.setContent(CONTENT);
        editNewsDto.setTitle(TITLE);
        editNewsDto.setContent(CONTENT);
    }

    @Test
    void testAddNews() {
        // Arrange
        News news = new News();
        news.setTitle(TITLE);
        news.setContent(CONTENT);
        news.setPublishDate(LocalDate.now());
        when(newsMapper.createNewsDtoToNews(any(CreateNewsDto.class))).thenReturn(news);
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        // Act
        NewsDto result = newsService.addNews(createNewsDto);

        // Assert
        Assertions.assertEquals(newsDto, result);
        verify(newsRepository, times(1)).save(news);
        verify(newsMapper, times(1)).newsToNewsDto(news);
    }

    @Test
    void testGetFilteredNews() {
        // Arrange
        List<News> newsList = new ArrayList<>();
        newsList.add(news);
        List<NewsDto> newsDtoList = new ArrayList<>();
        newsDtoList.add(newsDto);
        ResponseWrapperNewsDto responseWrapperNewsDto = new ResponseWrapperNewsDto();
        responseWrapperNewsDto.setCount(newsDtoList.size());
        responseWrapperNewsDto.setResults(newsDtoList);
        when(newsRepository.findAll((Specification<News>) any())).thenReturn(newsList);
        when(newsMapper.listNewsToNewsDto(newsList.size(), newsList)).thenReturn(responseWrapperNewsDto);

        // Act
        ResponseWrapperNewsDto result = newsService.getFilteredNews(null, null, null);

        // Assert
        Assertions.assertEquals(newsList.size(), result.getCount());
        Assertions.assertEquals(newsList.get(0).getTitle(), result.getResults().get(0).getTitle());
        verify(newsRepository, times(1)).findAll((Specification<News>) any());
        verify(newsMapper, times(1)).listNewsToNewsDto(newsList.size(), newsList);
    }

    @Test
    void testEditNews() {
        // Arrange
        Optional<News> optionalNews = Optional.of(news);
        when(newsRepository.findById(ID)).thenReturn(optionalNews);
        when(newsMapper.newsToEditNewsDto(news)).thenReturn(editNewsDto);

        // Act
        EditNewsDto result = newsService.editNews(ID, TITLE, CONTENT);

        // Assert
        Assertions.assertEquals(editNewsDto, result);
        verify(newsRepository, times(1)).findById(ID);
        verify(newsMapper, times(1)).newsToEditNewsDto(news);
    }

    @Test
    void testEditNews_ThrowsNewsNotFoundException() {
        // Arrange
        when(newsRepository.findById(ID)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NewsNotFoundException.class, () -> newsService.editNews(ID, TITLE, CONTENT));
        verify(newsRepository, times(1)).findById(ID);
        verify(newsMapper, never()).newsToEditNewsDto(any(News.class));
    }

    @Test
    void testDeleteNews() {
        // Arrange
        when(newsRepository.findById(ID)).thenReturn(Optional.of(news));
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        // Act
        NewsDto result = newsService.deleteNews(ID);

        // Assert
        Assertions.assertEquals(newsDto, result);
        verify(newsRepository, times(1)).findById(ID);
        verify(newsMapper, times(1)).newsToNewsDto(news);
        verify(newsRepository, times(1)).deleteById(ID);
    }

    @Test
    void testDeleteNews_ThrowsNewsNotFoundException() {
        // Arrange
        when(newsRepository.findById(ID)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NewsNotFoundException.class, () -> newsService.deleteNews(ID));
        verify(newsRepository, times(1)).findById(ID);
        verify(newsMapper, never()).newsToNewsDto(any(News.class));
        verify(newsRepository, never()).deleteById(ID);
    }

}