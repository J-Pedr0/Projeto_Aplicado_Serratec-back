package br.serratec.dev.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.dev.pa.model.Marca;

public interface  MarcaRepository extends JpaRepository< Marca, Long>{
    
}
