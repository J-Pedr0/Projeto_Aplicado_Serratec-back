package br.serratec.dev.pa.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.serratec.dev.pa.model.Ativo;

public class AtivoDTO {
    private String patrimonio;
    private String nroSerie;
    private String descricao;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAquisicao; 
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataGarantia;
    private Boolean statusAtivo;
    private String marca;
    private Long numeroManutencoes;
    private Long idAtivo;
    private LocalDate ultimaManutencao;
    public String ultimoStatus;
    public String ultimoUsuario;

    public AtivoDTO(){

    }

    public AtivoDTO(Ativo ativo){
        patrimonio = ativo.getPatrimonio();
        idAtivo = ativo.getIdAtivo();
        nroSerie = ativo.getNroSerie();
        descricao = ativo.getDescricao();
        dataAquisicao = ativo.getDataAquisicao();
        dataGarantia = ativo.getDataGarantia();
        statusAtivo = ativo.getStatusAtivo();
        marca = ativo.getMarca();
        idAtivo = ativo.getIdAtivo();


        
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public LocalDate getDataGarantia() {
        return dataGarantia;
    }

    public void setDataGarantia(LocalDate dataGarantia) {
        this.dataGarantia = dataGarantia;
    }

    public Boolean getStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(Boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getNumeroManutencoes() {
        return numeroManutencoes;
    }

    public void setNumeroManutencoes(Long numeroManutencoes) {
        this.numeroManutencoes = numeroManutencoes;
    }

    public Long getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Long idAtivo) {
        this.idAtivo = idAtivo;
    }

    public LocalDate getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(LocalDate ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public String getUltimoStatus() {
        return ultimoStatus;
    }

    public void setUltimoStatus(String ultimoStatus) {
        this.ultimoStatus = ultimoStatus;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

}
