package daoImpl;

import java.util.List;

import javax.persistence.Query;

import classes.LocaisFaseConcurso;
import dao.ILocaisFaseConcursoDAO;

public class LocaisFaseConcursoDAOImpl extends GenericDAOImpl<LocaisFaseConcurso> implements ILocaisFaseConcursoDAO {
    
	public LocaisFaseConcursoDAOImpl(){}
	@Override
	public void inserir(LocaisFaseConcurso entidade) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getManager().getTransaction().begin();
			this.getManager().persist(entidade);
			this.getManager().getTransaction().commit();
		} catch (Exception ex) {
			this.getManager().getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public void atualizar(LocaisFaseConcurso entidade) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getManager().getTransaction().begin();
			this.getManager().merge(entidade);
			this.getManager().getTransaction().commit();
		} catch (Exception ex) {
			this.getManager().getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public void remover(long id) throws Exception {
		// TODO Auto-generated method stub
	    LocaisFaseConcurso l = this.getManager().getReference(LocaisFaseConcurso.class,
				new Long(id));
		try {
			this.getManager().getTransaction().begin();
			this.getManager().remove(l);
			this.getManager().getTransaction().commit();
		} catch (Exception ex) {
			this.getManager().getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<LocaisFaseConcurso> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		Query query = this.getManager().createQuery(
				"SELECT l FROM LocaisFaseConcurso l");
		List<LocaisFaseConcurso> locaisFasesConcursos = (List<LocaisFaseConcurso>) query
				.getResultList();
		return locaisFasesConcursos;
	}

	@Override
	public LocaisFaseConcurso consultarPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		LocaisFaseConcurso l = this.getManager().find(LocaisFaseConcurso.class, new Long(id));
		return l;
	}

}
