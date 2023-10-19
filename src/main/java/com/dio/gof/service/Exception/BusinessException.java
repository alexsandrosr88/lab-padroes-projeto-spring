package com.dio.gof.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessException extends RuntimeException{
    public BusinessException(String mensagem){
        super(mensagem);
    }

    public BusinessException(String mensagem, Object ... params){
        super(String.format(mensagem, params));
    }
}
