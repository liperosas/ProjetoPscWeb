package negocios;

import classes.Genero;
import java.util.List;

import classes.QuestaoDiscursiva;
import daoImpl.QuestaoDiscursivaDAOImpl;
import factory.FactoryDAO;

public class RNQuestaoDiscursiva {

	private QuestaoDiscursivaDAOImpl dao;
	
	public RNQuestaoDiscursiva(){
		dao = FactoryDAO.getQuestaoDiscussivaDAOImpl();
	}
	
	public void inserir(QuestaoDiscursiva questaoDiscursiva) throws Exception{
		if(questaoDiscursiva.getElaborador() == null){
			throw new Exception("Quest�o com elaborador inv�lido!");
		}
		else if(questaoDiscursiva.getGenero() == null){
			throw new Exception("G�nero de quest�o inv�lido!");
		}
		else if(questaoDiscursiva.getTexto().equals("") || questaoDiscursiva.getTexto() == null){
			throw new Exception("Quest�o vazia!");
		}
		else{
			dao.inserir(questaoDiscursiva);
		}
	}
	
	public void atualizar(QuestaoDiscursiva questaoDiscursiva) throws Exception{
		
		if(questaoDiscursiva.getId() <= 0 ){
			throw new Exception("Id de quest�o inv�lido!");
		}
		else if(questaoDiscursiva.getElaborador().equals("") || questaoDiscursiva.getElaborador() == null){
			throw new Exception("Quest�o com elaborador inv�lido!");
		}
		else if(questaoDiscursiva.getGenero().equals("") || questaoDiscursiva.getGenero() == null){
			throw new Exception("G�nero de quest�o inv�lido!");
		}
		else if(questaoDiscursiva.getTexto().equals("") || questaoDiscursiva.getTexto() == null){
			throw new Exception("Quest�o vazia!");
		}
		else{
			dao.atualizar(questaoDiscursiva);
		}
	}
	
	public void remover(long id) throws Exception{
		
		if(id <=0){
			throw new Exception("Id de quest�o inv�lido!");
		}
		else{
			dao.remover(id);
		}
	}
	
	public QuestaoDiscursiva consultarPorId(long id) throws Exception{
		
		return dao.consultarPorId(id);
	}
	
	public List<QuestaoDiscursiva> consultarTodos() throws Exception{
		
		return dao.consultarTodos();
	}
        
        public List<QuestaoDiscursiva> consultarTodosPorGenero(Genero genero) throws Exception{
		
		return dao.consultarTodosPorGenero(genero);
	}
}
