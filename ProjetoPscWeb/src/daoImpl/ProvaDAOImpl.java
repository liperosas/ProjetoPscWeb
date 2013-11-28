package daoImpl;

import java.util.List;

import javax.persistence.Query;

import classes.Prova;
import dao.IProvaDAO;

public class ProvaDAOImpl extends GenericDAOImpl<Prova> implements IProvaDAO {

    public ProvaDAOImpl() {
    }

    @Override
    public void inserir(Prova entidade) throws Exception {
        // TODO Auto-generated method stub
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
    public void atualizar(Prova entidade) throws Exception {
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
        Prova p = this.getManager().getReference(Prova.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(p);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Prova> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT p FROM Prova p");
        List<Prova> provas = (List<Prova>) query
                .getResultList();
        return provas;
    }

    @Override
    public Prova consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        Prova p = this.getManager().find(Prova.class, new Long(id));
        return p;
    }
}
