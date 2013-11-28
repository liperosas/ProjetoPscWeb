package negocios;

import java.util.List;

import classes.Elaborador;
import daoImpl.ElaboradorDAOImpl;
import factory.FactoryDAO;

public class RNElaborador extends RNPessoa {

	ElaboradorDAOImpl dao;

	public void inserir(Elaborador elaborador) throws Exception {
		this.validarInserir(elaborador);
		dao.inserir(elaborador);
	}

	public void atualizar(Elaborador elaborador) throws Exception {
		this.validarAlterar(elaborador);
		dao.atualizar(elaborador);
	}

	public void remover(long id) throws Exception {
		this.validarRemover(id);
		dao.remover(id);
	}

	public Elaborador consultarPorId(long id) throws Exception {
		return dao.consultarPorId(id);
	}

	public List<Elaborador> consultarTodos() throws Exception {
		return dao.consultarTodos();
	}

	public RNElaborador() {
		// TODO Auto-generated constructor stub
		dao = FactoryDAO.getElaboradorDAOImpl();
	}

}
