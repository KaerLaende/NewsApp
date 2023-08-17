package com.github.KaerLaende.NewsApp.repository;

import com.github.KaerLaende.NewsApp.entity.Category;
import com.github.KaerLaende.NewsApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    /**
     * Метод написан используя параметр @Query сортированный по id,
     * так что бы сначало показывались последние новости
     * @return сортированная новостная лента
     */
    @Query(value = "select * from news order by id desc", nativeQuery = true)
    List<News> findAllNews();

    /**
     *  Метод написан с использованием ключевых слов Containing и IgnoreCase
     * @param title строка названия новости
     * @return лист новостей с таким названием
     */
    List<News> findByTitleContainingIgnoreCase(String title);

    /**
     *  Поиск списка новостей, относящихся к определенной категории
     * @param category категория, по которой будет осуществленн поиск новостей
     * @return лист новостей, соответствующих категории
     */
    List<News> findAllByCategory (Category category);
}
