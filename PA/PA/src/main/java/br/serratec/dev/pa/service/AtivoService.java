package br.serratec.dev.pa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.dev.pa.dto.AtivoDTO;
import br.serratec.dev.pa.dto.AtivoManutencaoDTO;
import br.serratec.dev.pa.exception.DataNotFoundException;
import br.serratec.dev.pa.exception.NumeroSerieException;
import br.serratec.dev.pa.exception.PatrimonioException;
import br.serratec.dev.pa.model.Ativo;
import br.serratec.dev.pa.model.Movimento;
import br.serratec.dev.pa.model.Status;
import br.serratec.dev.pa.repository.AtivoRepository;
import br.serratec.dev.pa.repository.MovimentoRepository;
import br.serratec.dev.pa.repository.StatusRepository;

@Service
public class AtivoService {
    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    AuthService authService;

    @Autowired
    private StatusRepository statusRepository;

    public List<Ativo> obterTodos() {
        return ativoRepository.findAll();
    }

    public AtivoDTO obterPorId(long id) throws DataNotFoundException {
        Optional<Ativo> ativo = ativoRepository.findById(id);
        if (!ativo.isPresent()) {
            throw new DataNotFoundException("Não foi possível encontrar esse ativo");
        }

        AtivoDTO ativoDTO = new AtivoDTO(ativo.get());
        ativoDTO.setNumeroManutencoes(ativoRepository.totalManutencao(ativoDTO.getIdAtivo()));
        ativoDTO.setUltimaManutencao(ativoRepository.ultimaManutencao(ativoDTO.getIdAtivo()));
        ativoDTO.setUltimoStatus(ativoRepository.ultimoStatus(ativoDTO.getIdAtivo()));
        ativoDTO.setUltimoUsuario(ativoRepository.ultimoUsuario(ativoDTO.getIdAtivo()));
        return ativoDTO;
    }

    public Ativo cadastrar(AtivoDTO ativoDTO) {
        Optional<Ativo> ativoOptional = ativoRepository.findByPatrimonio(ativoDTO.getPatrimonio());
        Optional<Ativo> ativoOptional2 = ativoRepository.findByNroSerie(ativoDTO.getNroSerie());

        if (ativoOptional.isPresent()) {
            throw new PatrimonioException("Já existe um ativo com este Patrimônio");
        }

        if (ativoOptional2.isPresent()) {
            throw new NumeroSerieException(
                    "Já existe um ativo com este Número de Série");
        }

        Ativo novoAtivo = new Ativo();

        novoAtivo.setPatrimonio(ativoDTO.getPatrimonio());
        novoAtivo.setNroSerie(ativoDTO.getNroSerie());
        novoAtivo.setDescricao(ativoDTO.getDescricao());
        novoAtivo.setDataAquisicao(ativoDTO.getDataAquisicao());
        novoAtivo.setDataGarantia(ativoDTO.getDataGarantia());
        novoAtivo.setMarca(ativoDTO.getMarca());
        novoAtivo.setStatusAtivo(ativoDTO.getStatusAtivo());

        novoAtivo = ativoRepository.save(novoAtivo);

        Movimento novoMovi = new Movimento();
        novoMovi.setDataInicio(LocalDate.now());
        novoMovi.setComentario("Cadastro de Produto");
        novoMovi.setAtivo(novoAtivo);
        int i = 1;
        Long n = Long.valueOf(i);
        Status statusBanco = statusRepository.findByIdStatus(n);
        novoMovi.setStatus(statusBanco);
        novoMovi = movimentoRepository.save(novoMovi);

        return novoAtivo;
    }

    public Ativo atualizar(Long id, AtivoDTO ativoDTO) {

        Optional<Ativo> ativo = ativoRepository.findById(id);
        if (!ativo.isPresent()) {
            throw new DataNotFoundException("Não foi possível encontrar esse ativo");
        }

        Ativo ativoBanco = ativo.get();

        ativoBanco.setPatrimonio(ativoDTO.getPatrimonio());
        ativoBanco.setNroSerie(ativoDTO.getNroSerie());
        ativoBanco.setDescricao(ativoDTO.getDescricao());
        ativoBanco.setDataAquisicao(ativoDTO.getDataAquisicao());
        ativoBanco.setDataGarantia(ativoDTO.getDataGarantia());
        ativoBanco.setStatusAtivo(ativoDTO.getStatusAtivo());
        ativoBanco.setMarca(ativoDTO.getMarca());

        ativoBanco = ativoRepository.save(ativoBanco);

        if (ativoDTO.getStatusAtivo() == false) {

            List<Movimento> listaMovimentoAnterior = movimentoRepository.listaMovimentoAtualDoAtivo(id);
            if (listaMovimentoAnterior.size() > 0) {
                for (Movimento movimentoAnterior : listaMovimentoAnterior) {
                    movimentoAnterior.setDataFim(LocalDate.now());
                    movimentoRepository.save(movimentoAnterior);
                }

                Movimento novoMovi = new Movimento();
                novoMovi.setDataInicio(LocalDate.now());
                novoMovi.setComentario("Inaticação de Ativo");
                novoMovi.setAtivo(ativoBanco);
                int i = 5;
                Long n = Long.valueOf(i);
                Status statusBanco = statusRepository.findByIdStatus(n);
                novoMovi.setStatus(statusBanco);
                novoMovi = movimentoRepository.save(novoMovi);

            }

        }

        return ativoBanco;
    }

    public void deletar(Long id) {
        obterPorId(id);
        ativoRepository.deleteById(id);
    }
    
    public List<AtivoManutencaoDTO> listaEquipamentos() {
        return ativoRepository.listaEquipamentos();
    }

    public List<AtivoManutencaoDTO> listaEquipamentosInativo() {
        return ativoRepository.listaEquipamentosInativo();
    }

    public Ativo deleteLogico(Long id) {
        Optional<Ativo> ativo = ativoRepository.findById(id);
        if (!ativo.isPresent()) {
            throw new DataNotFoundException("Não foi possível encontrar esse ativo");
        }

        List<Movimento> listaMovimentoAnterior = movimentoRepository.listaMovimentoAtualDoAtivo(id);
        if (listaMovimentoAnterior.size() > 0) {
            for (Movimento movimentoAnterior : listaMovimentoAnterior) {
                movimentoAnterior.setDataFim(LocalDate.now());
                movimentoRepository.save(movimentoAnterior);
            }
        }

        Ativo ativoBanco = ativo.get();

        ativoBanco.setStatusAtivo(false);

        ativoBanco = ativoRepository.save(ativoBanco);

        Movimento novoMovi = new Movimento();
        novoMovi.setDataInicio(LocalDate.now());
        novoMovi.setComentario("Inaticação de Ativo");
        novoMovi.setAtivo(ativoBanco);
        int i = 5;
        Long n = Long.valueOf(i);
        Status statusBanco = statusRepository.findByIdStatus(n);
        novoMovi.setStatus(statusBanco);
        novoMovi = movimentoRepository.save(novoMovi);

        return ativoBanco;
    }

    public Ativo ativacaoAtivo(Long id) {
        Optional<Ativo> ativo = ativoRepository.findById(id);
        if (!ativo.isPresent()) {
            throw new DataNotFoundException("Não foi possível encontrar esse ativo");
        }

        List<Movimento> listaMovimentoAnterior = movimentoRepository.listaMovimentoAtualDoAtivo(id);
        if (listaMovimentoAnterior.size() > 0) {
            for (Movimento movimentoAnterior : listaMovimentoAnterior) {
                movimentoAnterior.setDataFim(LocalDate.now());
                movimentoRepository.save(movimentoAnterior);
            }
        }

        Ativo ativoBanco = ativo.get();

        ativoBanco.setStatusAtivo(true);

        ativoBanco = ativoRepository.save(ativoBanco);

        Movimento novoMovi = new Movimento();
        novoMovi.setDataInicio(LocalDate.now());
        novoMovi.setComentario("Ativação de Ativo");
        novoMovi.setAtivo(ativoBanco);
        int i = 1;
        Long n = Long.valueOf(i);
        Status statusBanco = statusRepository.findByIdStatus(n);
        novoMovi.setStatus(statusBanco);
        novoMovi = movimentoRepository.save(novoMovi);

        return ativoBanco;
    }
}