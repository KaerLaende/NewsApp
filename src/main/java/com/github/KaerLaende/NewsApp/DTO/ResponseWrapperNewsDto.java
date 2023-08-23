package com.github.KaerLaende.NewsApp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperNewsDto {
    private Integer count;
    private List<NewsDto> results;
}

