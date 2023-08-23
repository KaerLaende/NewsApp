package com.github.KaerLaende.NewsApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Класс CreateNewsDto представляет объект передачи данных (DTO) для создания новости,
 * был использован record, введенный в 14 версии Java.
 *
 * @param title   Заголовок новости.
 * @param content Содержание новости.
 */


public record CreateNewsDto(@Size(min = 2, max = 30, message = "Заголовок новости должен содержать от 2 до 30 символов")
                            String title,
                            @NotBlank(message = "Содержание новости не может быть пустым")
                            String content,
                            CategoryDto categoryDto) {

}
