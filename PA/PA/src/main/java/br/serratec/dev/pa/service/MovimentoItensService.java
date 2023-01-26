package br.serratec.dev.pa.service;
// package br.serratec.t2m.sga.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import br.serratec.t2m.sga.exception.DataNotFoundException;
// import br.serratec.t2m.sga.model.MovimentoItens;
// import br.serratec.t2m.sga.repository.MovimentoItensRepository;

// @Service
// public class MovimentoItensService {
//         @Autowired
// 	private MovimentoItensRepository movimentoItensRepository;
	
// 	public List<MovimentoItens> obterTodos(){
// 		return movimentoItensRepository.findAll();
// 	}
	
// 	public MovimentoItens obterPorId(Long id) throws DataNotFoundException {
// 		Optional<MovimentoItens> movimentoItens = movimentoItensRepository.findById(id);
		
// 		if(!movimentoItens.isPresent()) {
// 			throw new DataNotFoundException("Não foi possível encontrar a MovimentoItens com id " + id);
// 		}
// 		return movimentoItens.get();
// 	}
    
// 	public MovimentoItens cadastrar(MovimentoItens movimentoItens) {
// 		movimentoItens.setIdMovimentoIten(null);
// 		return movimentoItensRepository.save(movimentoItens);
// 	}
	
// 	public MovimentoItens atualizar(Long id, MovimentoItens movimentoItens) {
// 		obterPorId(id);
// 		movimentoItens.setIdMovimentoIten(id);
// 		return movimentoItensRepository.save(movimentoItens);
// 	}
    
// 	public void deletar(Long id) {
// 		obterPorId(id);
// 		movimentoItensRepository.deleteById(id);
// 	}
	
// }
