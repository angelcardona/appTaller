package com.tallercarpro.appTaller.exception;

public class ResourceNotfoundException  extends RuntimeException{
    public ResourceNotfoundException(String message){
        super(message);
    }
    public  ResourceNotfoundException(String message,Throwable cause){
        super(message, cause);
    }
}
