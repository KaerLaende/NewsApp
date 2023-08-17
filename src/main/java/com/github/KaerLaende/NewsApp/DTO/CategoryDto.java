package com.github.KaerLaende.NewsApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс CategoryDto представляет объект передачи данных (DTO) в контроллеры для работы с категориями.
 */
@Data
public class CategoryDto {
    private Long id;
    private String categoryName;
}
