package daoImpl;

import classes.Genero;
import java.util.List;

import javax.persistence.Query;

import classes.QuestaoDiscursiva;
import dao.IQuestaoDiscussivaDAO;

public class QuestaoDiscursivaDAOImpl extends GenericDAOImpl<QuestaoDiscursiva> implements IQuestaoDiscussivaDAO {

    public QuestaoDiscursivaDAOImpl() {
    }

    @Override
    public void inserir(QuestaoDiscursiva entidade) throws Exception {
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
    public void atualizar(QuestaoDiscursiva entidade) throws Exception {
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
        QuestaoDiscursiva q = this.getManager().getReference(QuestaoDiscursiva.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(q);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<QuestaoDiscursiva> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT q FROM QuestaoDiscursiva q");
        List<QuestaoDiscursiva> questoesDiscussivas = (List<QuestaoDiscursiva>) query
                .getResultList();
        return questoesDiscussivas;
    }

    @Override
    public QuestaoDiscursiva consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        QuestaoDiscursiva q = this.getManager().find(QuestaoDiscursiva.class, new Long(id));
        return q;
    }

    @Override
    public List<QuestaoDiscursiva> consultarTodosPorGenero(Genero genero) throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT qd FROM QuestaoDiscursiva as qd where qd.genero = :id_genero");
        query.setParameter("id_genero", genero);
        List<QuestaoDiscursiva> questoesDiscussivas = (List<QuestaoDiscursiva>) query
                .getResultList();
        return questoesDiscussivas;
    }
}
