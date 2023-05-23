package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Pergunta implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String enunciado;
	
	public Pergunta() {
		
	}
	
	public Pergunta(Integer id, String enunciado) {
		this.id = id;
		this.enunciado = enunciado;
	}
	
	public Pergunta(String enunciado) {
		this.enunciado = enunciado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
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
		Pergunta other = (Pergunta) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Questao [id=" + id + ", enunciado=" + enunciado + "]";
	}
	
	
}
