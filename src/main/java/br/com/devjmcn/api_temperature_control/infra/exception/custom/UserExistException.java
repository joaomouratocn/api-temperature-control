package br.com.devjmcn.api_temperature_control.infra.exception.custom;

public class UserExistException extends RuntimeException{
    public UserExistException(String message){
        super(message);
    }
}
