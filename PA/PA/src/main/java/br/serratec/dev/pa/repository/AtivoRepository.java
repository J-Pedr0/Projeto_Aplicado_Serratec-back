package br.serratec.dev.pa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.serratec.dev.pa.dto.AtivoManutencaoDTO;
import br.serratec.dev.pa.model.Ativo;

public interface  AtivoRepository extends JpaRepository< Ativo, Long>{
   
    Ativo findByIdAtivo( Long idAtivo);
    Optional<Ativo> findByPatrimonio(String patrimonio);
    Optional<Ativo> findByNroSerie (String numeroSerie);
    Ativo findByDescricao( String descricao);
    Ativo findByDataAquisicao(LocalDate dataAquisicao);
    Ativo findByDataGarantia(LocalDate dataGarantia);
    Ativo findByMarca (String marca);

    
    @Query(value = "select "+
    " a.id_ativo idAtivo, "+
    " a.patrimonio patrimonio, "+
    " m.status_id statusId, "+
    " s.nome_status nomeStatus, "+
    " m.id_usuario idUsuario, "+
    " m.full_name fullName, "+
    " m.data_inicio dataInicio, "+
    "  "+
    " ( "+
    " select "+
    "         m2.data_inicio  "+
    " from "+
    "         movimento m2  "+
    " where  "+
    "         m2.ativo_id = a.id_ativo  "+
    "         and m2.status_id = 2 "+
    " order by  "+
    "         data_inicio desc limit 1 "+
    " ) as ultimaManutencao  "+
    " from  "+
    " ativo a  "+
    " inner join movimento m on (m.ativo_id = a.id_ativo) "+
    " inner join status s on (m.status_id = s.id_status) "+
    "  where "+
    " m.data_fim is null  "+
    "  and a.status_ativo = true"+
    " order by id_ativo ", nativeQuery = true

    ) 
    public List<AtivoManutencaoDTO>listaEquipamentos();

    @Query(value = "select "+
    " a.id_ativo idAtivo, "+
    " a.patrimonio patrimonio, "+
    " m.status_id statusId, "+
    " s.nome_status nomeStatus, "+
    " m.id_usuario idUsuario, "+
    " m.full_name fullName, "+
    " m.data_inicio dataInicio, "+
    "  "+
    " ( "+
    " select "+
    "         m2.data_inicio  "+
    " from "+
    "         movimento m2  "+
    " where  "+
    "         m2.ativo_id = a.id_ativo  "+
    "         and m2.status_id = 2 "+
    " order by  "+
    "         data_inicio desc limit 1 "+
    " ) as ultimaManutencao  "+
    " from  "+
    " ativo a  "+
    " inner join movimento m on (m.ativo_id = a.id_ativo) "+
    " inner join status s on (m.status_id = s.id_status) "+
    "  where "+
    " m.data_fim is null  "+
    "  and a.status_ativo = false"+
    " order by id_ativo ", nativeQuery = true

    ) 
    public List<AtivoManutencaoDTO>listaEquipamentosInativo();

    @Query(value = " select "+
    " count (*)"+
    " from "+
    " movimento "+
    " where "+
    " status_id = 2 "+
    " and "+
    " ativo_id = :idAtivo ", nativeQuery = true

    )
    public Long totalManutencao(@Param("idAtivo")Long idAtivo);


    @Query(value = " select "+
    "         m2.data_inicio  "+
    " from "+
    "         movimento m2  "+
    " where  "+
    "         m2.ativo_id = :idAtivo  "+
    "         and m2.status_id = 2 "+
    " order by  "+
    "         data_inicio desc limit 1 ", nativeQuery = true 
    )

    public LocalDate ultimaManutencao(@Param("idAtivo")Long idAtivo);

    @Query(value = " select "+         
   " s.nome_status "+
   " from "+         
   " movimento m2 join status s on s.id_status = m2.status_id "+
   " where "+         
   " m2.ativo_id = :idAtivo "+      
   " and "+
   " m2.data_fim is null "+
   " order by "+    
   " data_inicio desc limit 1 ", nativeQuery = true 
    )

    public String ultimoStatus(@Param("idAtivo")Long idAtivo);

    @Query(value = " select "+         
    " m2.full_name "+
    " from "+         
    " movimento m2 join status s on s.id_status = m2.status_id "+
    " where "+         
    " m2.ativo_id = :idAtivo "+      
    " and "+
    " m2.data_fim is null "+
    " order by "+    
    " data_inicio desc limit 1 ", nativeQuery = true 
     )
 
     public String ultimoUsuario(@Param("idAtivo")Long idAtivo);

}