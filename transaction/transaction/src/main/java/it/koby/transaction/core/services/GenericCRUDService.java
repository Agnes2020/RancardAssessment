package it.koby.transaction.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import it.koby.transaction.core.entities.Auditable;
import it.koby.transaction.core.repositories.GenericRepository;
import it.koby.transaction.exceptions.TransactionNotFoundException;

public class GenericCRUDService<Model extends Auditable> {
    
    public final GenericRepository<Model> genericRepository;

    public GenericCRUDService(GenericRepository<Model> genericRepository){
        this.genericRepository = genericRepository;
    }

    public Page<? extends Auditable> listAll(int pageNumber, int resultSize){
        return genericRepository.findAll(PageRequest.of(pageNumber, resultSize));
    }

    public Model getById(Long id){
        return genericRepository.findById(id).orElseThrow(() ->
            new TransactionNotFoundException("Transaction not found.")
        );
    }

    public Model create(Model request){
        return genericRepository.save(request);
    }

    public Model update(Model changes){
        return genericRepository.save(changes);
    }

    public void deleteById(Long id){
        Model existingModel = getById(id);
        genericRepository.delete(existingModel);
    }


}
