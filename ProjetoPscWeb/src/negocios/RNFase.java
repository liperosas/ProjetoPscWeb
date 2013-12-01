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
        if (fase.getLocal() == null) {
            throw new Exception("Escolha um local.");
        } else if (fase.getDiasFase() == null) {
            throw new Exception("Informe dia para a fase.");
        } else if (fase.getAreaconcurso() == null) {
            throw new Exception("Informe uma área de concurso para a fase");
        }
        faseDAOImpl.inserir(fase);
    }

    public void remover(long id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id inválido.");
        }
        faseDAOImpl.remover(id);
    }

    public void atualizar(Fase fase) throws Exception {
        if (fase.getLocal() == null) {
            throw new Exception("Escolha um local.");
        } else if (fase.getDiasFase() == null) {
            throw new Exception("Informe dia para a fase.");
        } else if (fase.getAreaconcurso() == null) {
            throw new Exception("Informe uma área de concurso para a fase");
        }
        faseDAOImpl.atualizar(fase);
    }

    public Fase consultarPorId(long id) throws Exception {
        return faseDAOImpl.consultarPorId(id);
    }
}
