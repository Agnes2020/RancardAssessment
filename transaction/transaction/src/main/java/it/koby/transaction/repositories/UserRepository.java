package it.koby.transaction.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import it.koby.transaction.core.repositories.GenericRepository;
import it.koby.transaction.core.users.User;

@Repository
public interface UserRepository extends GenericRepository<User> {
    
    public Optional<User> findByNameAndSurname(String name, String surname);
}
