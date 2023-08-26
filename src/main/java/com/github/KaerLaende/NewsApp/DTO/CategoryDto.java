package com.github.KaerLaende.NewsApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Класс CategoryDto представляет объект передачи данных (DTO) в контроллеры для работы с категориями.
 */
@Data
public class CategoryDto {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 30, message = "Название категории должно содержать от 2 до 30 символов")
    private String categoryName;
    @Override
    public String toString() {
        return "Category(id=" + id + ", categoryName=" + categoryName + ")";
    }
}
