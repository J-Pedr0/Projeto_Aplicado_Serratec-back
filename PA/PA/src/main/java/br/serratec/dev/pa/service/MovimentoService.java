package br.serratec.dev.pa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.dev.pa.dto.HistoricoMovimentoIdUsuarioDTO;
import br.serratec.dev.pa.dto.MovimentoDTO;
import br.serratec.dev.pa.exception.DataNotFoundException;
import br.serratec.dev.pa.model.Ativo;
import br.serratec.dev.pa.model.Movimento;
import br.serratec.dev.pa.model.Status;
import br.serratec.dev.pa.repository.AtivoRepository;
import br.serratec.dev.pa.repository.MovimentoRepository;
import br.serratec.dev.pa.repository.StatusRepository;

@Service
public class MovimentoService {
	@Autowired
	private MovimentoRepository movimentoRepository;

	@Autowired
	private AtivoRepository ativoRepository;

	@Autowired
	private StatusRepository statusRepository;

	public List<Movimento> obterTodos() {
		return movimentoRepository.findAll();
	}

	public Movimento obterPorId(Long id) throws DataNotFoundException {
		Optional<Movimento> movimento = movimentoRepository.findById(id);

		if (!movimento.isPresent()) {
			throw new DataNotFoundException("Não foi possível encontrar a Movimento com id " + id);
		}
		return movimento.get();
	}

	public Movimento cadastrar(Movimento movimento) {
		movimento.setIdMovimento(null);
		return movimentoRepository.save(movimento);
	}

	public Movimento atualizar(Long id, Movimento movimento) {
		obterPorId(id);
		movimento.setIdMovimento(id);
		return movimentoRepository.save(movimento);
	}

	public void deletar(Long id) {
		obterPorId(id);
		movimentoRepository.deleteById(id);
	}

	public Movimento inserirMovimento(MovimentoDTO movimentoDTO) {

		List<Movimento> listaMovimentoAnterior = movimentoRepository.listaMovimentoAtualDoAtivo(movimentoDTO.getIdAtivo());
		if (listaMovimentoAnterior.size() > 0) {
			for (Movimento movimentoAnterior : listaMovimentoAnterior) {
				movimentoAnterior.setDataFim(LocalDate.now());
				movimentoRepository.save(movimentoAnterior);
			}
		}

		Ativo ativoBanco = ativoRepository.findByIdAtivo(movimentoDTO.getIdAtivo());

		Status statusBanco = statusRepository.findByIdStatus(movimentoDTO.getIdStatus());

		Movimento moviNovo = new Movimento();
		moviNovo.setAtivo(ativoBanco);
		moviNovo.setDataInicio(movimentoDTO.getDataEntrega());
		moviNovo.setIdUsuario(movimentoDTO.getIdUsuario());
		moviNovo.setStatus(statusBanco);
		moviNovo.setFullName(movimentoDTO.getFullName());
		moviNovo.setComentario(movimentoDTO.getComentario());
		moviNovo = movimentoRepository.save(moviNovo);
		return moviNovo;
	}

	// public List<Movimento> historicoPorIdAtivo(Long idAtivo) {

	// 	Ativo ativoBanco = ativoRepository.findByIdAtivo(idAtivo);

	// 	return movimentoRepository.findByAtivo(ativoBanco);	
	// }

	public List<HistoricoMovimentoIdUsuarioDTO> historicoPorIdUsuario(String idUsuario) {
		List<Movimento> movimentos = movimentoRepository.findByIdUsuario(idUsuario);
		List<HistoricoMovimentoIdUsuarioDTO> movimentosIdUsuariosDTO = new ArrayList<>();
			for (Movimento movimento : movimentos){
				HistoricoMovimentoIdUsuarioDTO historicoUsuario = new HistoricoMovimentoIdUsuarioDTO();
				historicoUsuario.setIdStatus(movimento.getStatus().getIdStatus());
				historicoUsuario.setDescricao(ativoRepository.findByIdAtivo(movimento.getAtivo().getIdAtivo()).getDescricao());
				historicoUsuario.setDataEntrega(movimento.getDataInicio());
				historicoUsuario.setDataFim(movimento.getDataFim());
				historicoUsuario.setComentario(movimento.getComentario());

				movimentosIdUsuariosDTO.add(historicoUsuario);
			}
		return movimentosIdUsuariosDTO;
	}
	

}