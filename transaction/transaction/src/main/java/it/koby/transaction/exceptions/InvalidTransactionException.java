package it.koby.transaction.exceptions;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidTransactionException extends RuntimeException {

    List<?> voilatedConstraints;
    
    public InvalidTransactionException(String msg, List<?> violatedConstraints){
        super(msg);
        this.voilatedConstraints = violatedConstraints;
    }
}
