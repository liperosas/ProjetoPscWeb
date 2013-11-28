package daoImpl;

import java.util.List;

import javax.persistence.Query;


import classes.AreaConcurso;
import dao.IAreaConcursoDAO;

public class AreaConcursoDAOImpl extends GenericDAOImpl<AreaConcurso> implements IAreaConcursoDAO {
    public AreaConcursoDAOImpl(){}
	@Override
	public void inserir(AreaConcurso entidade) throws Exception {
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
	public void atualizar(AreaConcurso entidade) throws Exception {
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
		AreaConcurso a = this.getManager().getReference(AreaConcurso.class,
				new Long(id));
		try {
			this.getManager().getTransaction().begin();
			this.getManager().remove(a);
			this.getManager().getTransaction().commit();
		} catch (Exception ex) {
			this.getManager().getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<AreaConcurso> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		Query query = this.getManager().createQuery(
				"SELECT a FROM AreaConcurso a");
		List<AreaConcurso> areasConcurso = (List<AreaConcurso>) query
				.getResultList();
		return areasConcurso;
	}

	@Override
	public AreaConcurso consultarPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		AreaConcurso a = this.getManager().find(AreaConcurso.class, new Long(id));
		return a;
	}

}
