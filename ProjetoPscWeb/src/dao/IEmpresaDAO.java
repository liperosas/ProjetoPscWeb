package dao;

import java.util.List;

import classes.Empresa;

public interface IEmpresaDAO extends IGenericDAO<Empresa>{

	List<Empresa> consultarPorCnpj(String cnpj);
	
}
