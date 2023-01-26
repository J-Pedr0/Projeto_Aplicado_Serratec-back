package br.serratec.dev.pa.model;
// package br.serratec.t2m.sga.model;

// import java.util.Objects;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;
// import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// @Entity
// @Table(name = "movimento_itens")
// public class MovimentoItens {

// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	@Column(name = "id_movimento_itens")
// 	private Long idMovimentoIten;

// 	@NotNull
// 	private String idUsuario;

// 	@JsonIgnore
// 	@ManyToOne
// 	@JoinColumn(name = "id_movimento")
//     private Movimento movimento;
	

// 	public MovimentoItens(Long idMovimentoIten, @NotNull String idUsuario, Movimento movimento) {
// 		this.idMovimentoIten = idMovimentoIten;
// 		this.idUsuario = idUsuario;
// 		this.movimento = movimento;
// 	}

// 	public MovimentoItens() {
// 	}

// 	@Override
// 	public int hashCode() {
// 		return Objects.hash(idMovimentoIten);
// 	}

// 	@Override
// 	public boolean equals(Object obj) {
// 		if (this == obj)
// 			return true;
// 		if (obj == null)
// 			return false;
// 		if (getClass() != obj.getClass())
// 			return false;
// 		MovimentoItens other = (MovimentoItens) obj;
// 		return Objects.equals(idMovimentoIten, other.idMovimentoIten);
// 	}

// 	public Long getIdMovimentoIten() {
// 		return idMovimentoIten;
// 	}

// 	public void setIdMovimentoIten(Long idMovimentoIten) {
// 		this.idMovimentoIten = idMovimentoIten;
// 	}

// 	public String getIdUsuario() {
// 		return idUsuario;
// 	}

// 	public void setIdUsuario(String idUsuario) {
// 		this.idUsuario = idUsuario;
// 	}

// 	public Movimento getMovimento() {
// 		return movimento;
// 	}

// 	public void setMovimento(Movimento movimento) {
// 		this.movimento = movimento;
// 	}
    
// }
