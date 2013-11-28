/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.Gabarito;
import daoImpl.GabaritoDAOImpl;
import factory.FactoryDAO;
import java.util.List;

/**
 *
 * @author Antônio
 */
public class RNGabarito {

    private GabaritoDAOImpl dao;

    public RNGabarito() {
        dao = FactoryDAO.getGabaritoDAOImpl();
    }

    public void inserir(Gabarito gabarito) throws Exception {
        dao.inserir(gabarito);

    }

    public void atualizar(Gabarito gabarito) throws Exception {
        dao.atualizar(gabarito);
    }

    public void remover(long id) throws Exception {

        if (id <= 0) {
            throw new Exception("Id de gabarito inv�lido!");
        } else {
            dao.remover(id);
        }
    }

    public Gabarito consultarPorId(long id) throws Exception {

        return dao.consultarPorId(id);
    }

    public List<Gabarito> consultarTodos() throws Exception {
        return dao.consultarTodos();
    }
    
    public List<Gabarito> consultarGabaritoProva(long id_prova) throws Exception {
        return dao.consultarGabaritoProva(id_prova);
    }
}
