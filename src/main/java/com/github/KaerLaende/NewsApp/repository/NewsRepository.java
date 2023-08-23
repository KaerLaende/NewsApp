package com.github.KaerLaende.NewsApp.repository;

import com.github.KaerLaende.NewsApp.entity.Category;
import com.github.KaerLaende.NewsApp.entity.News;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {


    /**
     * Внутренний интерфейс для агрегации запросов к базе данных и поиска списка новостей.
     */

    interface Specs {

        /**
         * Метод уточняющий запрос, если указан Заголовок новости
         *
         * @param title строка названия новости
         * @return спецификация для поиска новостей по названию
         */

        static Specification<News> byTitleLike(String title) {
            return (root, query, criteriaBuilder) -> {
                if (title == null || title.isBlank()) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.like(root.get("title"), "%" + title + "%");
            };
        }

        /**
         * Метод уточняющий запрос для поиска новостей по содержимому.
         *
         * @param content содержимое новости
         * @return спецификация для поиска новостей по содержимому
         */
        static Specification<News> byContentLike(String content) {
            return (root, query, criteriaBuilder) -> {
                if (content == null || content.isBlank()) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.like(root.get("content"), "%" + content + "%");
            };
        }

        /**
         * Метод уточняющий запрос на поиск всех новостей относящихся к определенной категории
         *
         * @param category категория, по которой будет осуществленн поиск новостей
         * @return спецификация для поиска новостей по категории
         */
        static Specification<News> byCategoryEquals(String category) {
            return (root, query, criteriaBuilder) -> {
                if (category == null || category.isBlank()) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.like(root.get("category").get("name"), category);
            };
        }
    }

}
