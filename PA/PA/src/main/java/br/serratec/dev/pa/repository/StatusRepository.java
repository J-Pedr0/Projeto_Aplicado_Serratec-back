package br.serratec.dev.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.dev.pa.model.Status;

public interface  StatusRepository extends JpaRepository< Status, Long>{

    Status findByIdStatus(Long idStatus);
    
}
