package br.serratec.dev.pa.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idMarca;

    @NotBlank(message = "Qual a marca do produto?")
    @Column(name = "nome_marca", nullable = false, length = 250, unique = true)
    private String nomeMarca;

    
    public Marca() {
    }
    
    public Marca(Long idMarca, @NotBlank(message = "Qual a marca do produto?") String nomeMarca) {
        this.idMarca = idMarca;
        this.nomeMarca = nomeMarca;
    }
    
    public Long getIdMarca() {
        return idMarca;
    }
    
    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    @Override
	public int hashCode() {
		return Objects.hash(idMarca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
            Marca other = (Marca) obj;
		return Objects.equals(idMarca, other.idMarca);
	}

}