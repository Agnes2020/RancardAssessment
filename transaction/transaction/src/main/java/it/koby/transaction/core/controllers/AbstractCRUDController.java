package it.koby.transaction.core.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.koby.transaction.core.entities.Auditable;
import it.koby.transaction.core.services.GenericCRUDService;

public abstract class AbstractCRUDController<Model extends Auditable> {
    
    public GenericCRUDService<Model> service;

    public AbstractCRUDController(GenericCRUDService<Model> service){
        this.service = service;
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> listAll(@RequestParam("pageNo") int pageNumber, @RequestParam("size") int resultSize){
        return ResponseEntity.ok(service.listAll(pageNumber, resultSize));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findOneById(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOneById(@RequestParam("id") Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Deletion Success.");
    }

}
