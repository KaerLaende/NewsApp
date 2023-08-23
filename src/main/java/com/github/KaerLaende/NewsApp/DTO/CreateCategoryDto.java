package com.github.KaerLaende.NewsApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Класс CreateCategoryDto представляет объект передачи данных (DTO) для создания категории,
 * был использован record, введенный в 14 версии Java.
 *
 */


public record CreateCategoryDto(  @NotBlank(message = "Название категории не может быть пустым")
                                  @NotNull(message = "Название категории не может быть null")
                                  @Size(min = 2, max = 30, message = "Название категории должно содержать от 2 до 30 символов")
                                  String categoryName){

}

