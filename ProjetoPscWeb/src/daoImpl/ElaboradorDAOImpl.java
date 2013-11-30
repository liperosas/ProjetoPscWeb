package daoImpl;

import classes.Elaborador;
import dao.IElaboradorDAO;
import java.util.List;
import javax.persistence.Query;

public class ElaboradorDAOImpl extends GenericDAOImpl<Elaborador> implements IElaboradorDAO {
    
	public  ElaboradorDAOImpl(){}
	@Override
	public void inserir(Elaborador entidade) throws Exception {
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
	public void atualizar(Elaborador entidade) throws Exception {
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
        
        
//        public void remover(Elaborador entidade) throws Exception {
//		// TODO Auto-generated method stub
//		try {
//			this.getManager().getTransaction().begin();
//			this.getManager().merge(entidade);
//			this.getManager().getTransaction().commit();
//		} catch (Exception ex) {
//			this.getManager().getTransaction().rollback();
//			throw new Exception(ex.getMessage());
//		}
//	}

	@Override
	public void remover(long id) throws Exception {
		// TODO Auto-generated method stub
		Elaborador e = this.getManager().getReference(Elaborador.class,
				new Long(id));
		try {
			this.getManager().getTransaction().begin();
			this.getManager().remove(e);
			this.getManager().getTransaction().commit();
		} catch (Exception ex) {
			this.getManager().getTransaction().rollback();
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<Elaborador> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		Query query = this.getManager().createQuery(
				"SELECT e FROM Elaborador e");
		List<Elaborador> elaboradores = (List<Elaborador>) query
				.getResultList();
		return elaboradores;
		
	}

	@Override
	public Elaborador consultarPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		Elaborador e = this.getManager().find(Elaborador.class, new Long(id));
		return e;
	}

    

}
