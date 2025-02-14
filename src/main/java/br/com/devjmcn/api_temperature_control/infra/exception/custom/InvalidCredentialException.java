package br.com.devjmcn.api_temperature_control.infra.exception.custom;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message){super(message);}
}
