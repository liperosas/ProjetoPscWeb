package daoImpl;

import java.util.List;

import javax.persistence.Query;


import classes.Local;
import dao.ILocalDAO;

public class LocalDAOImpl extends GenericDAOImpl<Local> implements ILocalDAO {
    public LocalDAOImpl(){}
	
	@Override
	public void inserir(Local entidade) throws Exception {
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
	public void atualizar(Local entidade) throws Exception {
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
		Local l = this.getManager().getReference(Local.class,
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
	public List<Local> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		Query query = this.getManager().createQuery(
				"SELECT l FROM Local l");
		List<Local> locais = (List<Local>) query
				.getResultList();
		return locais;
	}

	@Override
	public Local consultarPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		Local l = this.getManager().find(Local.class, new Long(id));
		return l;
	}

}
