package application;

import model.dao.AlternativaDao;
import model.dao.DaoFactory;
import model.entities.Alternativa;

public class Program {

	public static void main(String[] args) {

		AlternativaDao alternativaDao = DaoFactory.createAlternativaDao();
		
		Alternativa alternativa = alternativaDao.findById(2);
		
		System.out.println(alternativa);

	}

}
