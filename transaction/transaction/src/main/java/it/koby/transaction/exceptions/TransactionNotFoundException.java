package it.koby.transaction.exceptions;

public class TransactionNotFoundException extends RuntimeException {
    
    public TransactionNotFoundException(String msg){
        super(msg);
    }
}
