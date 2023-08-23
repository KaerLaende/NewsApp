package com.github.KaerLaende.NewsApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 30, message = "Заголовок новости должно содержать от 2 до 30 символов")
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
