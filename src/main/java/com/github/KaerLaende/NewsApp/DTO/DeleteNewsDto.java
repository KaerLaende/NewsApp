package com.github.KaerLaende.NewsApp.DTO;

import java.time.LocalDate;
/**
 * Класс DeleteNewsDto представляет объект передачи данных (DTO) в контроллеры для удаления новости.
 */
public record DeleteNewsDto(Long id, String title, LocalDate localDate) {
}
