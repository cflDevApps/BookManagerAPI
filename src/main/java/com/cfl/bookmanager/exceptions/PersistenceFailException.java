package com.cfl.bookmanager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class PersistenceFailException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

}
