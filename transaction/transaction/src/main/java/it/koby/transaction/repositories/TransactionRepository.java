package it.koby.transaction.repositories;

import org.springframework.stereotype.Repository;

import it.koby.transaction.core.repositories.GenericRepository;
import it.koby.transaction.entities.Transaction;

@Repository
public interface TransactionRepository extends GenericRepository<Transaction> {
    
}
