package br.serratec.dev.pa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.dev.pa.exception.DataNotFoundException;
import br.serratec.dev.pa.model.Status;
import br.serratec.dev.pa.repository.StatusRepository;

@Service
public class StatusService {
        @Autowired
	private StatusRepository statusRepository;
	
	public List<Status> obterTodos(){
		return statusRepository.findAll();
	}
	
	public Status obterPorId(Long id) throws DataNotFoundException {
		Optional<Status> status = statusRepository.findById(id);
		
		if(!status.isPresent()) {
			throw new DataNotFoundException("Não foi possível encontrar a Status com id " + id);
		}
		return status.get();
	}
    
	public Status cadastrar(Status status) {

		Status statusNovo = new Status();

		statusNovo.setStatus(status.getStatus());

		statusNovo = statusRepository.save(statusNovo);
		return statusNovo;

		// status.setIdStatus(null);
		// return statusRepository.save(status);
	}
	
	public Status atualizar(Long id, Status status) {
		obterPorId(id);
		status.setIdStatus(id);
		return statusRepository.save(status);
	}

	public void deletar(Long id) {
		obterPorId(id);
		statusRepository.deleteById(id);
	}
	
}
