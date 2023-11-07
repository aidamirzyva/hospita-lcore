package com.bookstore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LibraryException extends RuntimeException{

    private Integer code;
    public LibraryException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        //getCode
        return code;
    }
}
