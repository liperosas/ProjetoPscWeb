/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.Empresa;
import classes.Endereco;
import daoImpl.EmpresaDAOImpl;
import factory.FactoryDAO;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class RNEmpresa {

    private EmpresaDAOImpl dao;

    public RNEmpresa() {
        dao = FactoryDAO.getEmpresaDAOImpl();
    }

    public boolean validaTelefone(String telefone) {
        return telefone.matches("\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{4}?");
    }

    public boolean validarEndereco(Endereco endereco) throws Exception {
        if (endereco.getBairro().equals("") || endereco.getBairro() == null) {
            throw new Exception("Bairro inv�lido");
        }/* else if (validarCEP(endereco.getCep()) == false
         || endereco.getCep().equals("") || endereco.getCep() == null) {
         throw new Exception("Cep inv�lido");
         }*/ else if (endereco.getCidade().equals("")
                || endereco.getCidade() == null) {
            throw new Exception("Cidade inv�lida");
        } else if (endereco.getLogradouro().equals("")
                || endereco.getLogradouro() == null) {
            throw new Exception("Logradouro inv�lido");
        } else if (endereco.getNumero().equals("")
                || endereco.getNumero() == null) {
            throw new Exception("N�mero inv�lido");
        } else if (endereco.getUf().equals("") || endereco.getUf() == null) {
            throw new Exception("UF inv�lido");
        }
        return false;
    }

    public boolean validarCEP(String cep) {
        return cep.matches("\\[0-9]{5}?\\-[0-9]{3}?");
    }

    public void inserir(Empresa empresa) throws Exception {
        if (empresa.getNome().trim().equals("") || empresa.getNome() == null) {
            throw new Exception("Nome da empresa inválido.");
        } else if (empresa.getCnpj().trim().equals("") || empresa.getCnpj() == null) {
            throw new Exception("CNPJ da empresa inválido.");
        }else if (dao.consultarPorCnpj(empresa.getCnpj()).size() > 0){
        	throw new Exception("CNPJ da empresa ja cadastrado.");
        } else if (!validaTelefone(empresa.getTelefone())) {
            throw new Exception("Telefone da empresa inválido.");
        } else if (validarEndereco(empresa.getEndereco())) {
            throw new Exception("Endereço da empresa inválido.");
        } else {
            dao.inserir(empresa);
        }
    }

    public void atualizar(Empresa empresa) throws Exception {
        if (empresa.getId() <= 0) {
            throw new Exception("Id inválido.");
        } else if (empresa.getNome().trim().equals("") || empresa.getNome() == null) {
            throw new Exception("Nome da empresa inválido.");
        } else if (empresa.getCnpj().trim().equals("") || empresa.getCnpj() == null) {
            throw new Exception("CNPJ da empresa inválido.");
        } else if (!validaTelefone(empresa.getTelefone())) {
            throw new Exception("Telefone da empresa inválido.");
        } else if (validarEndereco(empresa.getEndereco())) {
            throw new Exception("Endereço da empresa inválido.");
        } else {
            dao.atualizar(empresa);
        }
    }

    public void remover(long id) throws Exception{
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        else{
            dao.remover(id);
        }
    }
    
    public List<Empresa> consultarTodos() throws Exception {
		return dao.consultarTodos();
	}
    
    public Empresa consultarPorId(long id) throws Exception {
		return dao.consultarPorId(id);
	}
}
