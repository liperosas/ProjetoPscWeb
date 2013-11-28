/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import classes.DiaFase;
import dao.IDiaFaseDAO;
import java.util.List;

/**
 *
 * @author aluno
 */
public class DiaFaseDAOImpl extends GenericDAOImpl<DiaFase> implements IDiaFaseDAO {

    @Override
    public void inserir(DiaFase entidade) throws Exception {
        try {
            this.getManager().getTransaction().begin();
            this.getManager().persist(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void atualizar(DiaFase entidade) throws Exception {
        try {
            this.getManager().getTransaction().begin();
            this.getManager().merge(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void remover(long id) throws Exception {
        DiaFase d = this.getManager().getReference(DiaFase.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(d);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<DiaFase> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DiaFase consultarPorId(long id) throws Exception {
        DiaFase d = this.getManager().find(DiaFase.class, new Long(id));
        return d;
    }
}
