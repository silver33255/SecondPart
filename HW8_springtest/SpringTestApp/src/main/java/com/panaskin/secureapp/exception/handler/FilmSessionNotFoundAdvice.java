package com.panaskin.secureapp.exception.handler;
import com.panaskin.secureapp.exception.FilmSessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FilmSessionNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(FilmSessionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String filmSessionNotFoundHandler(FilmSessionNotFoundException ex) {
        return ex.getMessage();
    }
}
