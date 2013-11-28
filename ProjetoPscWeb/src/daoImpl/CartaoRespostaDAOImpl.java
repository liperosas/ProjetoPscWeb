package daoImpl;

import classes.CartaoResposta;
import dao.ICartaoRespostaDAO;
import java.util.List;
import javax.persistence.Query;

public class CartaoRespostaDAOImpl extends GenericDAOImpl<CartaoResposta> implements ICartaoRespostaDAO {

    public CartaoRespostaDAOImpl() {
    }

    @Override
    public void inserir(CartaoResposta entidade) throws Exception {
        // TODO Auto-generated method stub
        this.getManager().getTransaction().begin();
        try {
            this.getManager().persist(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void atualizar(CartaoResposta entidade) throws Exception {
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
        CartaoResposta c = this.getManager().getReference(CartaoResposta.class,
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
    public List<CartaoResposta> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT c FROM CartaoRersposta c");
        List<CartaoResposta> cartoesRespostas = (List<CartaoResposta>) query
                .getResultList();
        return cartoesRespostas;
    }

    @Override
    public CartaoResposta consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        CartaoResposta c = this.getManager().find(CartaoResposta.class, new Long(id));
        return c;
    }
}
