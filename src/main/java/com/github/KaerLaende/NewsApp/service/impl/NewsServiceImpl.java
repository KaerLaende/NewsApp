package com.github.KaerLaende.NewsApp.service.impl;

import com.github.KaerLaende.NewsApp.DTO.*;
import com.github.KaerLaende.NewsApp.entity.News;
import com.github.KaerLaende.NewsApp.exception.NewsNotFoundException;
import com.github.KaerLaende.NewsApp.mapper.NewsMapper;
import com.github.KaerLaende.NewsApp.repository.NewsRepository;
import com.github.KaerLaende.NewsApp.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.github.KaerLaende.NewsApp.repository.NewsRepository.Specs.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsDto addNews(CreateNewsDto createNewsDto) {
        News news = newsMapper.createNewsDtoToNews(createNewsDto);
        news.setPublishDate(LocalDate.now());
        log.info("создается новость:" + news);
        newsRepository.save(news);
        return newsMapper.newsToNewsDto(news);
    }


    @Override
    public ResponseWrapperNewsDto getFilteredNews(String category, String name, String content) {
        List<News> newsList = newsRepository.findAll(
                byTitleLike(name)
                        .and(byContentLike(content))
                        .and(byCategoryEquals(category))
        );
        return newsMapper.listNewsToNewsDto(newsList.size(), newsList);
    }

    @Override
    public EditNewsDto editNews(long id, String title, String content) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            if (title != null) {
                news.setTitle(title);
            }
            if (content != null) {
                news.setContent(content);
            }
            return newsMapper.newsToEditNewsDto(news);
        } else {
            throw new NewsNotFoundException(id);
        }
    }

@Override
    public NewsDto deleteNews(long id){
        News news= newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);
        log.info("Удаление новости с id = {}", id);
        NewsDto newsDto = newsMapper.newsToNewsDto(news);
        newsRepository.deleteById(id);
        return newsDto;
    }

}
