package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Membre;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Membre} entity.
 */
@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
    
}
