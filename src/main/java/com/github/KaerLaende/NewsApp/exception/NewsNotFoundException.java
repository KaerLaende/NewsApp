package com.github.KaerLaende.NewsApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException{

public NewsNotFoundException(){
super("News not found");
}

public NewsNotFoundException(long id){ super("News not found"+id);}
}
