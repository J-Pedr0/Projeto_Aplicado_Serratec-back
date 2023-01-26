package br.serratec.dev.pa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.serratec.dev.pa.model.Ativo;
import br.serratec.dev.pa.model.Movimento;

public interface  MovimentoRepository extends JpaRepository< Movimento, Long>{
    
    @Query(value = "select m from Movimento m join m.ativo a where m.dataFim is null and a.idAtivo = :idAtivo")
    List<Movimento> listaMovimentoAtualDoAtivo(@Param("idAtivo") Long idAtivo);

    public List<Movimento>findByAtivo(Ativo ativo);

    public List<Movimento>findByIdUsuario(String idUsuario);
}
