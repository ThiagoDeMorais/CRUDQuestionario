package application;

import java.util.List;
import java.util.Scanner;

import model.dao.AlternativaDao;
import model.dao.DaoFactory;
import model.dao.PerguntaDao;
import model.entities.Alternativa;
import model.entities.Pergunta;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		AlternativaDao alternativaDao = DaoFactory.createAlternativaDao();
		PerguntaDao perguntaDao = DaoFactory.createPerguntaDao();
		
		System.out.println("=== TESTE 1: Alternativa findByID ===");
		
		Alternativa alternativa = alternativaDao.findById(2);
		
		System.out.println(alternativa);
		
		System.out.println("\n=== TESTE 2: Alternativa findByPergunta ===");
		Pergunta pergunta = new Pergunta(2,"null");
		List<Alternativa> list = alternativaDao.findByPergunta(pergunta);

		for(Alternativa obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 3: Alternativa método findAll ===");
		list = alternativaDao.findAll();

		for(Alternativa obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 4: Alternativa método Insert ===");
		Alternativa novaAlternativa = new Alternativa("Todas estao certas", false, pergunta);
		alternativaDao.insert(novaAlternativa);
		System.out.println("Inserido! Novo id igual a " + novaAlternativa.getId());
		
		System.out.println("\n=== TESTE 5: Alternativa método Update ===");
		alternativa = alternativaDao.findById(1);
		alternativa.setConteudo("Não sei");
		
		alternativaDao.update(alternativa);
		
		//System.out.println("\n=== TESTE 6: Alternativa método Delete ===");
		//System.out.println("Digite um id da alternativa que deseja deletar");
		//int id = sc.nextInt();
		//alternativaDao.deleteById(id);
		//System.out.println("Deleção completada");
		
		System.out.println("=== TESTE 7: Pergunta findByID ===");
		Pergunta pergunta2 = perguntaDao.findById(1);
		
		System.out.println(pergunta2);
		
		System.out.println("\n=== TESTE 8: Pergunta método findAll ===");
		List<Pergunta> listPergunta = perguntaDao.findAll(); 
		 
		for(Pergunta obj : listPergunta) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 9: Pergunta método Insert ===");
		Pergunta novaPergunta = new Pergunta(null, "Quem foi napoleão?");
		perguntaDao.insert(novaPergunta);
		System.out.println("Inserido! Novo id igual a " + novaPergunta.getId());
		
		System.out.println("\n=== TESTE 10: Pergunta método Update ===");
		pergunta = perguntaDao.findById(6);
		pergunta.setEnunciado("Quem foi Julio Cesar? ");
		
		perguntaDao.update(pergunta);
		
		System.out.println("\n=== TESTE 6: Pergunta método Delete ===");
		System.out.println("Digite um id da pergunta que deseja deletar");
		int id = sc.nextInt();
		perguntaDao.deleteById(id);
		System.out.println("Deleção completada");
		
		sc.close();

	}

}
