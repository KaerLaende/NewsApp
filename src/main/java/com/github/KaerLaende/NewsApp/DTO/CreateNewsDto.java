package com.github.KaerLaende.NewsApp.DTO;

/**
 * Класс CreateNewsDto представляет объект передачи данных (DTO) для создания новости.
 * Он использует рекорд, введенный в 14 версии Java.
 * @param title   Заголовок новости.
 * @param content Содержание новости.
 */


public record  CreateNewsDto(String title, String content) {

}
