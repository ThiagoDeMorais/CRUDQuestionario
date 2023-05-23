package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Alternativa implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String conteudo;
	private Boolean ehVerdadeira;
	private Pergunta pergunta;
	
	public Alternativa() {
		
	}

	public Alternativa(String conteudo, boolean ehVerdadeira, Pergunta pergunta) {
		this.conteudo = conteudo;
		this.ehVerdadeira = ehVerdadeira;
		this.pergunta = pergunta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Boolean EhVerdadeira() {
		return ehVerdadeira;
	}

	public void setEhVerdadeira(Boolean ehVerdadeira) {
		this.ehVerdadeira = ehVerdadeira;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alternativa other = (Alternativa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Alternativa [id=" + id + ", conteudo=" + conteudo + ", ehVerdadeira=" + ehVerdadeira + ", pergunta="
				+ pergunta + "]";
	}
}
