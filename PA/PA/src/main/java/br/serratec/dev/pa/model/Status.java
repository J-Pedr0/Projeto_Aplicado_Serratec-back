package br.serratec.dev.pa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long idStatus;

    @NotBlank
    @Column(name = "nome_status", nullable = false, length = 250)
    private String status;

    @Column(name = "status_obrigatorio")
    private Boolean statusObrigatorio;

    @Column(name = "status_manutencao")
    private Boolean statusManutencao;

    public Status() {
    }

    public Status(Long idStatus, @NotBlank String status, Boolean statusObrigatorio, Boolean statusManutencao) {
        this.idStatus = idStatus;
        this.status = status;
        this.statusObrigatorio = statusObrigatorio;
        this.statusManutencao = statusManutencao;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getStatusObrigatorio() {
        return statusObrigatorio;
    }

    public void setStatusObrigatorio(Boolean statusObrigatorio) {
        this.statusObrigatorio = statusObrigatorio;
    }

    public Boolean getStatusManutencao() {
        return statusManutencao;
    }

    public void setStatusManutencao(Boolean statusManutencao) {
        this.statusManutencao = statusManutencao;
    }

}