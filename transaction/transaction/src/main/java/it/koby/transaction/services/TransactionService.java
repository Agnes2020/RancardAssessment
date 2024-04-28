package it.koby.transaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.koby.transaction.core.services.GenericCRUDService;
import it.koby.transaction.entities.Transaction;
import it.koby.transaction.repositories.TransactionRepository;
import it.koby.transaction.requests.CreateTransactionRequest;

@Service
public class TransactionService extends GenericCRUDService<Transaction> {

    @Autowired
    private UserService userService;

    TransactionService(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }

    public Transaction makeTransaction(CreateTransactionRequest request){
        return create(createTransaction(request));
    }

    public Transaction updateTransaction(Long id, CreateTransactionRequest request){
        Transaction existingTransaction = getById(id);
        Transaction newTransaction = createTransaction(request);
        existingTransaction.setReceiver(newTransaction.getReceiver());
        existingTransaction.setSender(newTransaction.getSender());
        existingTransaction.setAmount(newTransaction.getAmount());
        return update(existingTransaction);
    }

    private Transaction createTransaction(CreateTransactionRequest request) {
        Transaction newTransaction = new Transaction();
        newTransaction.setReceiver(userService.findByFirstNameAndLastName(request.getReceiverName()));
        newTransaction.setSender(userService.findByFirstNameAndLastName(request.getSenderName()));
        newTransaction.setAmount(request.getAmountToSend());
        return newTransaction;
    }

}
