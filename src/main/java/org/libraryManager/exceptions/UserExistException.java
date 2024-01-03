package org.libraryManager.exceptions;

public class UserExistException extends RuntimeException{
    public  UserExistException(String message){
        super(message);
    }
}
