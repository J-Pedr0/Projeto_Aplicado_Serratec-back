package br.serratec.dev.pa.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface AtivoManutencaoDTO {
    
    public Long getIdAtivo();
    public String getPatrimonio();
    public Long getStatusId();
    public String getNomeStatus();
    public String getIdUsuario();
    public String getFullName();
    @JsonFormat (pattern = "dd/MM/yyyy")
    public LocalDate getDataInicio();
    @JsonFormat (pattern = "dd/MM/yyyy")
    public LocalDate getUltimaManutencao();


}
