/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.Fase;
import daoImpl.FaseDAOImpl;
import factory.FactoryDAO;

public class RNFase {

    FaseDAOImpl faseDAOImpl;

    public RNFase() {
        faseDAOImpl = FactoryDAO.getFaseDAOImpl();
    }

    public void inserir(Fase fase) throws Exception {
        faseDAOImpl.inserir(fase);
    }

    public void remover(long id) throws Exception {
        faseDAOImpl.remover(id);
    }

    public void atualizar(Fase fase) throws Exception {
        faseDAOImpl.atualizar(fase);
    }

    public Fase consultarPorId(long id) throws Exception {
        return faseDAOImpl.consultarPorId(id);
    }
}
