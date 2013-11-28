package daoImpl;

import java.util.List;

import javax.persistence.Query;

import classes.Fase;
import dao.IFaseDAO;

public class FaseDAOImpl extends GenericDAOImpl<Fase> implements IFaseDAO {

    public FaseDAOImpl() {
    }

    @Override
    public void inserir(Fase entidade) throws Exception {
        // TODO Auto-generated method stub
        this.getManager().getTransaction().begin();
        try {

            this.getManager().persist(entidade);

        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
        this.getManager().getTransaction().commit();
    }

    @Override
    public void atualizar(Fase entidade) throws Exception {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        Fase f = this.getManager().getReference(Fase.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(f);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Fase> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT f FROM Fase f");
        List<Fase> fases = (List<Fase>) query
                .getResultList();
        return fases;
    }

    @Override
    public Fase consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        Fase f = this.getManager().find(Fase.class, new Long(id));
        return f;
    }
}
