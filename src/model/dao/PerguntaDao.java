package model.dao;

import java.util.List;

import model.entities.Pergunta;

public interface PerguntaDao {
	void insert(PerguntaDao obj);
	void update(PerguntaDao obj);
	void deleteById(Integer id);
	Pergunta findById(Integer id);
	List<Pergunta> findAll();

}
