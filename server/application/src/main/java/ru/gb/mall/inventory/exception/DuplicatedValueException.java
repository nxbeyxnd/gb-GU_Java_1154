package ru.gb.mall.inventory.exception;

public class DuplicatedValueException extends RuntimeException{
    public DuplicatedValueException(String message){
        super (message);
    }

    public DuplicatedValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedValueException(Throwable cause) {
        super(cause);
    }
}
