package com.github.KaerLaende.NewsApp.mapper;

import com.github.KaerLaende.NewsApp.DTO.CreateNewsDto;
import com.github.KaerLaende.NewsApp.DTO.EditNewsContentDto;
import com.github.KaerLaende.NewsApp.entity.News;
import org.mapstruct.*;




@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NewsMapper {

    /**
     * Сопоставляет объект {@link CreateNewsDto} в объект {@link News}
     */
    News createNewsDtoToNews(CreateNewsDto createNewsDto);

    /**
     * Сопоставляет объект {@link EditNewsContentDto} в объект {@link News}
     */
    News editNewsContentDtoToNews(EditNewsContentDto editNewsContentDto);


}
