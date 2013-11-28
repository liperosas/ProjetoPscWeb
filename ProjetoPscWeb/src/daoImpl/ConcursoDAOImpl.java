package daoImpl;

import java.util.List;

import javax.persistence.Query;

import classes.Concurso;
import dao.IConcursoDAO;

public class ConcursoDAOImpl extends GenericDAOImpl<Concurso> implements IConcursoDAO {

    public ConcursoDAOImpl() {
    }

    @Override
    public void inserir(Concurso entidade) throws Exception {
        // TODO Auto-generated method stub
        this.getManager().getTransaction().begin();
        try {
            this.getManager().persist(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            //throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Concurso entidade) throws Exception {
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
        Concurso c = this.getManager().getReference(Concurso.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(c);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Concurso> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT c FROM Concurso c");
        List<Concurso> concursos = (List<Concurso>) query
                .getResultList();
        return concursos;
    }

    @Override
    public Concurso consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        Concurso c = this.getManager().find(Concurso.class, new Long(id));
        return c;
    }
}
