package com.github.KaerLaende.NewsApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{

public CategoryNotFoundException(long id){
super("Category not found" +id);
}
    public CategoryNotFoundException(){
        super("Category not found");
    }

    public CategoryNotFoundException(String s) {
        super(s);

    }
}

