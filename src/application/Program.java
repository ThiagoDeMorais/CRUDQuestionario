package application;

import java.util.List;

import model.dao.AlternativaDao;
import model.dao.DaoFactory;
import model.entities.Alternativa;
import model.entities.Pergunta;

public class Program {

	public static void main(String[] args) {

		AlternativaDao alternativaDao = DaoFactory.createAlternativaDao();
		
		System.out.println("=== TESTE 1: Alternativa findByID ===");
		
		Alternativa alternativa = alternativaDao.findById(2);
		
		System.out.println(alternativa);
		
		System.out.println("\n=== TESTE 2: Alternativa findByPergunta ===");
		Pergunta pergunta = new Pergunta(2,"null");
		List<Alternativa> list = alternativaDao.findByPergunta(pergunta);

		for(Alternativa obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 3: Alternativa findAll ===");
		list = alternativaDao.findAll();

		for(Alternativa obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 4: Alternativa Insert ===");
		Alternativa novaAlternativa = new Alternativa("Todas estao certas", false, pergunta);
		alternativaDao.insert(novaAlternativa);
		System.out.println("Inserido! Novo id igual a " + novaAlternativa.getId());
	}

}
