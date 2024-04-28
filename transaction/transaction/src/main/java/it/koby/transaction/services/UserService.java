package it.koby.transaction.services;

import org.springframework.stereotype.Service;

import it.koby.transaction.core.services.GenericCRUDService;
import it.koby.transaction.core.users.User;
import it.koby.transaction.exceptions.UserNotFoundException;
import it.koby.transaction.repositories.UserRepository;

@Service
public class UserService extends GenericCRUDService<User> {

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public User findByFirstNameAndLastName(String name){
        return ((UserRepository) genericRepository).findByNameAndSurname(
            name.split(" ")[0],
            name.split(" ")[1]
        ).orElseThrow(() -> new UserNotFoundException(String.format("User %s not found", name)));
    }
    
}
