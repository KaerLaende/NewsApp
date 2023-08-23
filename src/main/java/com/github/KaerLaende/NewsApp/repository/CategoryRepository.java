package com.github.KaerLaende.NewsApp.repository;

import com.github.KaerLaende.NewsApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Метод написан используя параметр @Query сортированный по id
     * @return сортированный список категорий
     */
    @Query(value = "select * from category order by id", nativeQuery = true)
    List<Category> findAllCategory();

    /**
     * Метод для поиска категории по id
     * @param id идентификатор категории
     * @return найденная категория
     */
    Optional<Category> findById(Long id);

    /**
     * Метод для удаления категории по id
     * @param id идентификатор категории
     */
    void deleteById(Long id);

}
