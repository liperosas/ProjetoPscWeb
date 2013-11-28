package daoImpl;

import java.util.List;

import javax.persistence.Query;


import classes.Genero;

import dao.IGeneroDAO;

public class GeneroDAOImpl extends GenericDAOImpl<Genero> implements IGeneroDAO {
    public GeneroDAOImpl(){}
	@Override 
	public void inserir(Genero entidade) throws Exception {
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
	public void atualizar(Genero entidade) throws Exception {
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
		Genero g = this.getManager().getReference(Genero.class,
				new Long(id));
		try {
			this.getManager().getTransaction().begin();
			this.getManager().remove(g);
			this.getManager().getTransaction().commit();
		} catch (Exception ex) {
			this.getManager().getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<Genero> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		Query query = this.getManager().createQuery(
				"SELECT g FROM Genero g");
		List<Genero> generos = (List<Genero>) query
				.getResultList();
		return generos;
	}

	@Override
	public Genero consultarPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		Genero g = this.getManager().find(Genero.class, new Long(id));
		return g;
		
	}


}
