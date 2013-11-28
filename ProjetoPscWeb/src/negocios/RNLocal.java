/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.Endereco;
import classes.Local;
import daoImpl.LocalDAOImpl;
import factory.FactoryDAO;
import java.util.List;

/**
 *
 * @author RAFAELSANTIAGO
 */
public class RNLocal {
    
    LocalDAOImpl dao;
    public RNLocal(){
     dao= FactoryDAO.getLocalDAOImpl();
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
        
        public void inserir (Local local)throws Exception{
           
            dao.inserir(local);
        }
        
        public void atualizar (Local local)throws Exception{
         dao.atualizar(local);
        }
       
         public void remover(long id) throws Exception{
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        else{
            dao.remover(id);
        }
    }
    
    public List<Local> consultarTodos() throws Exception {
		return dao.consultarTodos();
	}
    
    public Local consultarPorId(long id) throws Exception {
		return dao.consultarPorId(id);
	}
    
    
}
