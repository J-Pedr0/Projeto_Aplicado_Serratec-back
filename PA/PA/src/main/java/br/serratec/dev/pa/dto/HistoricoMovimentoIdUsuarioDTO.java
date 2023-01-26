package br.serratec.dev.pa.dto;

import java.time.LocalDate;

public class HistoricoMovimentoIdUsuarioDTO {
    
    private Long idStatus;
    private String descricao;   
    private LocalDate dataEntrega;
    private LocalDate dataFim;
    private String comentario;

    public Long getIdStatus() {
        return idStatus;
    }
    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
