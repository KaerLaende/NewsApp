package com.github.KaerLaende.NewsApp.controller;


import com.github.KaerLaende.NewsApp.DTO.*;
import com.github.KaerLaende.NewsApp.exception.CategoryNotFoundException;
import com.github.KaerLaende.NewsApp.service.CategoryService;
import com.github.KaerLaende.NewsApp.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 * API контроллер для работы с новостной лентой и отдельными новостями
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/news")
@Tag(name = "Новостная лента", description = "Работа с новостной лентой и отдельными новостями")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешный запрос"),
        @ApiResponse(responseCode = "400", description = "Невалидные параметры запроса"),
        @ApiResponse(responseCode = "404", description = "Результат запроса не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка программы", content = @Content)
})
public class NewsController {
    private final NewsService newsService;
    private final CategoryService categoryService;

    @PostMapping()
    @Operation(summary = "Создание новостей")
    @Description(value = "Для создания новости напишите заголовок и выберите категорию из имеющихся")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NewsDto.class))),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    public ResponseEntity<NewsDto> createNews(
            @RequestParam("title") @Valid @NotNull @NotBlank @Size(min = 3, max = 30) String title,
            @RequestParam("content") @Valid @NotNull @NotBlank @Size(min = 3) String content,
            @RequestBody CategoryDto categoryDto,
            Model model // заготовка для выпадающего списка со всеми Категориями
    ) {
        log.info("Отправлен запрос на создание новости");

        // Передача списка категорий в представление,
        // ожидается что категорию будут выбирать из списка и вкладывать в requestBody
        model.addAttribute("categories", categoryService.findAllCategory());

        if (!categoryService.checkCategoryExists(categoryDto)) {
            String info = "Введенной категории- " + categoryDto.getCategoryName() + "не найдено в базе";
            log.info(info);
            throw new CategoryNotFoundException("Такой категории нет, вы можете её добавить самостоятельно");
        }

        CreateNewsDto createNewsDto = new CreateNewsDto(title, content, categoryDto);

        return ResponseEntity.ok(newsService.addNews(createNewsDto));
    }

    @Operation(
            summary = "Получить список всех новостей",
            description = "Возможно настроить фильтр поиска по категориям, заголовку и(или) контенту. " +
                    "Так же можно указать количество новостей на 1 странице и выбрать страницу списка.")
    @GetMapping
    public List<NewsDto> getAllFilteredNews(
            @RequestParam(defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String content

    ) {
        ResponseWrapperNewsDto responseWrapper = newsService.getFilteredNews(category, name, content);
        List<NewsDto> newsDtoList = responseWrapper.getResults();
        log.info("запрос на отображение всех новостей");
        int totalCount = newsDtoList.size();
        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalCount);
        List<NewsDto> subList = newsDtoList.subList(start, end);
        log.info("вывод подлиста" + subList);
        return subList;
    }
    @Operation(
            summary = "Отредактировать контент и заголовок новости",
            description = "Возможно изменить конект и (или) заголовок новости")
    @PatchMapping("{id}")
    public ResponseEntity<EditNewsDto> editNewsContent(
            @PathVariable("id") long id,
            @RequestParam(required = false, name = "title") @Valid @Size(min = 2, max = 30) String title,
            @RequestParam(required = false, name = "content") @Valid @Size(min = 2) String content
    ) {
        log.info("Запрос на редактирование новостей");
        return ResponseEntity.ok(newsService.editNews(id, title, content));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить новость по id")

    public ResponseEntity<NewsDto> deleteNews(@PathVariable("id") long id) {
        log.info("Удаление категории с id = {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
