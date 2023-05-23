package model.dao;

import java.util.List;

import model.entities.Pergunta;

public interface PerguntaDao {
	void insert(Pergunta obj);
	void update(Pergunta obj);
	void deleteById(Integer id);
	Pergunta findById(Integer id);
	List<Pergunta> findAll();
}
