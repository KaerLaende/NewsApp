package com.github.KaerLaende.NewsApp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Сущность для работы с категориями, к которым возможно отнести новость.
 */
@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<News> news;

}