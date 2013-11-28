package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import classes.Empresa;
import classes.Funcionario;
import dao.IEmpresaDAO;

public class EmpresaDAOImpl extends GenericDAOImpl<Empresa> implements IEmpresaDAO {

	public EmpresaDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inserir(Empresa entidade) throws Exception {
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
	public void atualizar(Empresa entidade) throws Exception {
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
		this.getManager().getTransaction().begin();
		Empresa e = this.getManager().getReference(Empresa.class, new Long(id));
		this.getManager().remove(e);
		this.getManager().getTransaction().commit();

	}

	@Override
	public List<Empresa> consultarTodos() throws Exception {
		// TODO Auto-generated method stub
		Query query = this.getManager().createQuery("SELECT e FROM Empresa e");
		List<Empresa> empresas = (List<Empresa>) query.getResultList();
		return empresas;
	}

	@Override
	public Empresa consultarPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		Empresa e = this.getManager().find(Empresa.class, new Long(id));
		return e;
	}

	@Override
	public List<Empresa> consultarPorCnpj(String cnpj) {
		// TODO Auto-generated method stub
		List<Empresa> empresas = new ArrayList<Empresa>();
		Query query = this.getManager().createQuery("SELECT e from Empresa e where cnpj = :cnpj");
		query.setParameter("cnpj", cnpj);
		empresas = query.getResultList();
		return empresas;
	}
	
	

}
