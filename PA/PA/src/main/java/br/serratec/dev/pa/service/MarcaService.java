package br.serratec.dev.pa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.dev.pa.exception.DataNotFoundException;
import br.serratec.dev.pa.model.Marca;
import br.serratec.dev.pa.repository.MarcaRepository;

@Service
public class MarcaService {
        @Autowired
	private MarcaRepository marcaRepository;
	
	public List<Marca> obterTodos(){
		return marcaRepository.findAll();
	}
	
	public Marca obterPorId(Long id) throws DataNotFoundException {
		Optional<Marca> marca = marcaRepository.findById(id);
		
		if(!marca.isPresent()) {
			throw new DataNotFoundException("Não foi possível encontrar a Marca com id " + id);
		}
		return marca.get();
	}
    
	public Marca cadastrar(Marca marca) {

		Marca marcaNova = new Marca();

		marcaNova.setNomeMarca(marca.getNomeMarca());

		marcaNova = marcaRepository.save(marcaNova);
		return marcaNova;

		// marca.setIdMarca(null);
		// return marcaRepository.save(marca);
	}
	
	public Marca atualizar(Long id, Marca marca) {
		obterPorId(id);
		marca.setIdMarca(id);
		return marcaRepository.save(marca);
	}

	public void deletar(Long id) {
		obterPorId(id);
		marcaRepository.deleteById(id);
	}
	
}
