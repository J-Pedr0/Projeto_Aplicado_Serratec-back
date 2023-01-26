package br.serratec.dev.pa.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ativo")
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ativo")
    @ApiModelProperty(value = "Identificador unico de Ativo")
    private Long idAtivo;

    @NotBlank
    @Column(name = "patrimonio", nullable = false, length = 10, unique = true)
    @ApiModelProperty(value = "Patrimonio do ativo")
    private String patrimonio;

    @NotBlank
    @Column(name = "nro_serie", nullable = false, length = 20, unique = true)
    @ApiModelProperty(value = "Número de série do ativo")
    private String nroSerie;

    @NotBlank
    @Size(min = 1, message = "Deve haver descrição do ativo")
    @Column(name = "descricao", nullable = false, length = 250)
    @ApiModelProperty(value = "Descrição do ativo")
    private String descricao;

    @NotNull
    @Column(name = "data_aquisicao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "Data de aquisição do ativo")
    private LocalDate dataAquisicao;

    @NotNull
    @Column(name = "data_garantia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "Data de garantia do ativo")
    private LocalDate dataGarantia;

    @NotNull
    @Column(name = "status_ativo")
    private Boolean statusAtivo;

    @NotBlank
    @ApiModelProperty(value = "Marca do ativo")
    private String marca;

    public Ativo() {
    }

    public Ativo(Long idAtivo, @NotBlank String patrimonio, @NotBlank String nroSerie,
            @NotBlank @Size(min = 10, message = "Deve haver descrição do ativo") String descricao,
            @NotNull LocalDate dataAquisicao, @NotNull LocalDate dataGarantia, Boolean statusAtivo,
            @NotNull String marca) {
        this.idAtivo = idAtivo;
        this.patrimonio = patrimonio;
        this.nroSerie = nroSerie;
        this.descricao = descricao;
        this.dataAquisicao = dataAquisicao;
        this.dataGarantia = dataGarantia;
        this.statusAtivo = statusAtivo;
        this.marca = marca;
    }

    public Long getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Long idAtivo) {
        this.idAtivo = idAtivo;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtivo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ativo other = (Ativo) obj;
        return Objects.equals(idAtivo, other.idAtivo);
    }

    public Boolean getStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(Boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

}