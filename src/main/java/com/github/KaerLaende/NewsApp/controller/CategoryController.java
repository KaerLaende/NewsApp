package com.github.KaerLaende.NewsApp.controller;


import com.github.KaerLaende.NewsApp.DTO.CategoryDto;
import com.github.KaerLaende.NewsApp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * API контроллер для работы с категориями новостей
 */
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/category")
@Tag(name = "Категории", description = "Работа с категориями новостей")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешный запрос"),
        @ApiResponse(responseCode = "400", description = "Невалидные параметры запроса"),
        @ApiResponse(responseCode = "404", description = "Результат запроса не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка программы", content = @Content)
})
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping()
    @Operation(summary = "Создание новой категории новостей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    public ResponseEntity<CategoryDto> createCategory(@RequestPart("name") @Valid @Size(min = 2, max = 30) String name) {
        log.info("Создание новой категории новостей");
        return ResponseEntity.ok(categoryService.addCategory(name));
    }


    @Operation(summary = "Получить все категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = CategoryDto.class)))),
    })
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        log.info("Запрос всех категорий");
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменить название категории новостей")
    public ResponseEntity<CategoryDto> editCategory(@PathVariable("id") long id, @RequestPart("name") @Valid @Size(min = 2, max = 30) String name) {
        log.info("Редактирование категории новостей");
        return ResponseEntity.ok(categoryService.editCategory(id, name));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию по id")

    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("id") long id) {
        log.info("Удаление категории с id = {}", id);
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }


}
