package daoImpl;

import java.util.List;

import javax.persistence.Query;

import classes.Alternativa;
import dao.IAlternativaDAO;

public class AlternativaDAOImpl extends GenericDAOImpl<Alternativa> implements
        IAlternativaDAO {

    public AlternativaDAOImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void inserir(Alternativa entidade) throws Exception {
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
    public void atualizar(Alternativa entidade) throws Exception {
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
        Alternativa a = this.getManager().getReference(Alternativa.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(a);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Alternativa> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT a FROM Alternativa a");
        List<Alternativa> resultList = (List<Alternativa>) query
                .getResultList();
        List<Alternativa> alternativas = resultList;
        return alternativas;
    }

    @Override
    public Alternativa consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        Alternativa a = this.getManager().find(Alternativa.class, new Long(id));
        return a;
    }

    @Override
    public Alternativa inserirAlternativa(Alternativa entidade) throws Exception {
        // TODO Auto-generated method stub
        try {
            this.getManager().getTransaction().begin();
            this.getManager().persist(entidade);
            this.getManager().getTransaction().commit();
            return entidade;
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }
    
}
