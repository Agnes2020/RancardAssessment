package it.koby.transaction.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.koby.transaction.core.controllers.AbstractCRUDController;
import it.koby.transaction.entities.Transaction;
import it.koby.transaction.requests.CreateTransactionRequest;
import it.koby.transaction.services.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController extends AbstractCRUDController<Transaction> {

    public TransactionController(TransactionService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransation(@NotNull @Valid @RequestBody CreateTransactionRequest request){
        return ResponseEntity.ok(((TransactionService) service).makeTransaction(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTransaction(@RequestParam("id") Long id, @NotNull @Valid @RequestBody CreateTransactionRequest request){
        return ResponseEntity.ok(((TransactionService) service).updateTransaction(id, request));
    }
    
}
