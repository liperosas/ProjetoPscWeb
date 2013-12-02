/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.CartaoResposta;
import daoImpl.CartaoRespostaDAOImpl;
import factory.FactoryDAO;
import java.util.List;

/**
 *
 * @author Antônio
 */
public class RNCartaoResposta {

    private CartaoRespostaDAOImpl dao;

    public RNCartaoResposta() {
        dao = FactoryDAO.getCartaoRespostaDAOImpl();
    }

    public void inserir(CartaoResposta cartaoResposta) throws Exception {
        dao.inserir(cartaoResposta);

    }

    public void atualizar(CartaoResposta cartaoResposta) throws Exception {
        dao.atualizar(cartaoResposta);
    }

    public void remover(long id) throws Exception {

        if (id <= 0) {
            throw new Exception("Id de prova inv�lido!");
        } else {
            dao.remover(id);
        }
    }

    public CartaoResposta consultarPorId(long id) throws Exception {

        return dao.consultarPorId(id);
    }

    public List<CartaoResposta> consultarTodos() throws Exception {
        return dao.consultarTodos();
    }
}
