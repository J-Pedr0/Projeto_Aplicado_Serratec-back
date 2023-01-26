package br.serratec.dev.pa.service;
// package br.serratec.t2m.sga.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import br.serratec.t2m.sga.exception.DataNotFoundException;
// import br.serratec.t2m.sga.model.Colaborador;
// import br.serratec.t2m.sga.repository.ColaboradorRepository;

// @Service
// public class ColaboradorService {
//         @Autowired
// 	private ColaboradorRepository colaboradorRepository;
	
// 	public List<Colaborador> obterTodos(){
// 		return colaboradorRepository.findAll();
// 	}
	
// 	public Colaborador obterPorId(Long id) throws DataNotFoundException{
// 		Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
		
// 		if(!colaborador.isPresent()) {
// 			throw new DataNotFoundException("Não foi possível encontrar a Colaborador com id " + id);
// 		}
// 		return colaborador.get();
// 	}
    
// 	public Colaborador cadastrar(Colaborador colaborador) {
// 		colaborador.setIdColaborador(null);
// 		return colaboradorRepository.save(colaborador);
// 	}
	
// 	public Colaborador atualizar(Long id, Colaborador colaborador) {
// 		obterPorId(id);
// 		colaborador.setIdColaborador(id);
// 		return colaboradorRepository.save(colaborador);
// 	}

// 	public void deletar(Long id) {
// 		obterPorId(id);
// 		colaboradorRepository.deleteById(id);
// 	}
	
// }
