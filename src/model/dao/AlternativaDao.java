package model.dao;

import java.util.List;

import model.entities.Alternativa;

public interface AlternativaDao {
	void insert(AlternativaDao obj);
	void update(AlternativaDao obj);
	void deleteById(Integer id);
	Alternativa findById(Integer id);
	List<Alternativa> findAll();

}
