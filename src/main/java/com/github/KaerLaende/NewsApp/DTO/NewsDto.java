package com.github.KaerLaende.NewsApp.DTO;


import lombok.Data;

import java.time.LocalDate;

/**
 * Класс NewsDto представляет объект передачи данных (DTO) в контроллеры для работы с новостями.
 */
@Data
public class NewsDto {

    private long id;
    private String category;
    private String title;
    private String content;
    private LocalDate publishDate;

}
