package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Questao implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String enunciado;
	
	public Questao() {
		
	}
	
	public Questao(String enunciado) {
		this.enunciado = enunciado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		Questao other = (Questao) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Questao [id=" + id + ", enunciado=" + enunciado + "]";
	}
	
	
}
