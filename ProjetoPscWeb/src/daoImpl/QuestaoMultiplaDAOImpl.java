package daoImpl;

import classes.Genero;
import classes.QuestaoDiscursiva;
import java.util.List;

import javax.persistence.Query;


import classes.QuestaoMultiplaEscolha;
import dao.IQuestaoMultiplaDAO;

public class QuestaoMultiplaDAOImpl extends GenericDAOImpl<QuestaoMultiplaEscolha> implements IQuestaoMultiplaDAO {

    public QuestaoMultiplaDAOImpl() {
    }

    @Override
    public void inserir(QuestaoMultiplaEscolha entidade) throws Exception {
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
    public void atualizar(QuestaoMultiplaEscolha entidade) throws Exception {
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
        QuestaoMultiplaEscolha q = this.getManager().getReference(QuestaoMultiplaEscolha.class,
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
    public List<QuestaoMultiplaEscolha> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT q FROM QuestaoMultiplaEscolha q");
        List<QuestaoMultiplaEscolha> questoesMultiplasEscolhas = (List<QuestaoMultiplaEscolha>) query
                .getResultList();
        return questoesMultiplasEscolhas;
    }

    @Override
    public QuestaoMultiplaEscolha consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        QuestaoMultiplaEscolha q = this.getManager().find(QuestaoMultiplaEscolha.class, new Long(id));
        return q;
    }
    
    @Override
    public List<QuestaoMultiplaEscolha> consultarTodosPorGenero(Genero genero) throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT qme FROM QuestaoMultiplaEscolha as qme where qme.genero = :id_genero");
        query.setParameter("id_genero", genero);
        List<QuestaoMultiplaEscolha> questoesMultiplaEscolha = (List<QuestaoMultiplaEscolha>) query
                .getResultList();
        return questoesMultiplaEscolha;
    }
}
