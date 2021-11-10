package com.shangma.exception;


import com.shangma.http.AxiosResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandel {

    @ExceptionHandler(ApiException.class)
    public AxiosResult catchHandel(ApiException apiException){
        return AxiosResult.error(apiException.getAxiosStatus());
    }
}
