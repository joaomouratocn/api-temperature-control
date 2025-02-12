package br.com.devjmcn.api_temperature_control.infra.exception.custom;

public class NotDataFoundException extends RuntimeException{
    public NotDataFoundException(String message){
        super(message);
    }
}
