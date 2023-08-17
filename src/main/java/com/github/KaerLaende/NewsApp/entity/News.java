package com.github.KaerLaende.NewsApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name ="news")
@Data
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    @NotBlank
    private String content;
    private String author;
    @NotNull
    private LocalDateTime publishDate;


}
