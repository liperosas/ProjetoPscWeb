/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.DiaFase;
import daoImpl.DiaFaseDAOImpl;
import factory.FactoryDAO;

public class RNDiaFase {

    private DiaFaseDAOImpl diaFaseDAOImpl;

    public RNDiaFase() {
        diaFaseDAOImpl = FactoryDAO.getDiaFaseDAOImpl();
    }

    public void inserir(DiaFase diaFase) throws Exception {
        diaFaseDAOImpl.inserir(diaFase);
    }

    public void remover(long id) throws Exception {
        diaFaseDAOImpl.remover(id);
    }

    public void atualizar(DiaFase diaFase) throws Exception {
        diaFaseDAOImpl.atualizar(diaFase);
    }

    public DiaFase consultarPorId(long id) throws Exception {
        return diaFaseDAOImpl.consultarPorId(id);
    }
}
