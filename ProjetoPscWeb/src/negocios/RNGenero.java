/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.Genero;
import daoImpl.GeneroDAOImpl;
import factory.FactoryDAO;
import java.util.List;

/**
 *
 * @author Antônio
 */
public class RNGenero {

    private GeneroDAOImpl dao;

    public RNGenero() {
        dao = FactoryDAO.getGeneroDAOImpl();
    }

    public void inserir(Genero genero) throws Exception {
        if (genero.getGenero().equals("") || genero.getGenero() == null) {
            throw new Exception("Favor informar um gênero válido");
        }
        dao.inserir(genero);
    }

    public void atualizar(Genero genero) throws Exception {
        if (genero.getGenero().equals("") || genero.getGenero() == null) {
            throw new Exception("Favor informar um gênero válido");
        }
        dao.atualizar(genero);
    }

    public void remover(long id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id inválido.");
        }
        dao.remover(id);
    }

    public Genero consultarPorId(long id) throws Exception {
        return dao.consultarPorId(id);
    }

    public List<Genero> consultarTodos() throws Exception {
        return dao.consultarTodos();
    }
}
