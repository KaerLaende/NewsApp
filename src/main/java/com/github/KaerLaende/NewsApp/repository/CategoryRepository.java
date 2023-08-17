package com.github.KaerLaende.NewsApp.repository;

import com.github.KaerLaende.NewsApp.entity.Category;
import com.github.KaerLaende.NewsApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Метод написан используя параметр @Query сортированный по id
     * @return сортированный список категорий
     */
    @Query(value = "select * from news order by id", nativeQuery = true)
    List<Category> findAllCategory();


}
