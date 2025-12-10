package com.example.demo.exception;

public class NotFindIdException extends RuntimeException {
    public NotFindIdException(){
        super("Usuario não encontrado");
    }

    public NotFindIdException(String msg){
        super(msg);
    }
}
