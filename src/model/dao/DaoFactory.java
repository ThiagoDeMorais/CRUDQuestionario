package model.dao;

import model.dao.impl.AlternativaDaoJDBC;

public class DaoFactory {

	public static AlternativaDao createAlternativaDao() {
		return new AlternativaDaoJDBC();
	}
}
