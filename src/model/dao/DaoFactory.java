package model.dao;

import db.DB;
import model.dao.impl.AlternativaDaoJDBC;
import model.dao.impl.PerguntaDaoJDBC;

public class DaoFactory {

	public static AlternativaDao createAlternativaDao() {
		return new AlternativaDaoJDBC(DB.getConnection());
	}
	
	public static PerguntaDao createPerguntaDao() {
		return new PerguntaDaoJDBC(DB.getConnection());
	}
}
