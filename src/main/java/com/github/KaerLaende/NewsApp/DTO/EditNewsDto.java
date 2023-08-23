package com.github.KaerLaende.NewsApp.DTO;

import lombok.Data;

/**
 * Класс EditNewsContentDto представляет объект передачи данных (DTO) в контроллеры для редактировании содержания новости.
 */
@Data
public class EditNewsDto {
    private long id;
    private String title;
    private String content;
}
