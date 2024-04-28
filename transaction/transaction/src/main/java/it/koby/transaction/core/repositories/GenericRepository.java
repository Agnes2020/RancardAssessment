package it.koby.transaction.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.koby.transaction.core.entities.Auditable;

@NoRepositoryBean
public interface GenericRepository<Model extends Auditable> extends JpaRepository<Model, Long> {
    
}
