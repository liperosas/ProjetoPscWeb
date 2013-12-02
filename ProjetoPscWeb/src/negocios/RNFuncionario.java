package negocios;

import java.util.List;

import classes.Funcionario;
import daoImpl.FuncionarioDAOImpl;
import factory.FactoryDAO;

public class RNFuncionario extends RNPessoa {

	FuncionarioDAOImpl dao;

	public void inserir(Funcionario funcionario) throws Exception {
		this.validarInserir(funcionario);
		dao.inserir(funcionario);
	}

	public void atualizar(Funcionario funcionario) throws Exception {
		this.validarAlterar(funcionario);
		dao.atualizar(funcionario);
	}

	public void remover(long id) throws Exception {
		this.validarRemover(id);
		dao.remover(id);
	}

	public Funcionario consultarPorId(long id) throws Exception {
		return dao.consultarPorId(id);
	}

	public List<Funcionario> consultarTodos() throws Exception {
		return dao.consultarTodos();
	}

	public RNFuncionario() {
		// TODO Auto-generated constructor stub
		dao = FactoryDAO.getFuncionarioDAOImpl();
	}

}
