package com.minicare.Exception;

public class MiniCareException extends RuntimeException{
    public MiniCareException(String message){
        super(message);
    }

    public MiniCareException(Throwable t){
        super(t);
    }
}
