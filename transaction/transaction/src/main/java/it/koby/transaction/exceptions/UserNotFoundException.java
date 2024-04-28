package it.koby.transaction.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg){
        super(msg);
    }
}
