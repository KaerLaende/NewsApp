package com.github.KaerLaende.NewsApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Класс EditNewsContentDto представляет объект передачи данных (DTO) в контроллеры для редактировании содержания новости.
 */
@Data
public class EditNewsDto {
    @Size(min = 2, max = 30, message = "Заголовок новости должно содержать от 2 до 30 символов")
    private String title;
    @NotBlank
    private String content;
}
