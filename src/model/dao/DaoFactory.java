package model.dao;

import db.DB;
import model.dao.impl.AlternativaDaoJDBC;

public class DaoFactory {

	public static AlternativaDao createAlternativaDao() {
		return new AlternativaDaoJDBC(DB.getConnection());
	}
}
