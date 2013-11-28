package negocios;

import classes.Genero;
import classes.QuestaoDiscursiva;
import java.util.List;

import classes.QuestaoMultiplaEscolha;
import daoImpl.QuestaoMultiplaDAOImpl;
import factory.FactoryDAO;

public class RNQuestaoMultipla {

    private QuestaoMultiplaDAOImpl dao;

    public RNQuestaoMultipla() {
        dao = FactoryDAO.getQuestaoMultiplaDAOImpl(); 
    }

    public void inserir(QuestaoMultiplaEscolha questaoMultiplaEscolha) throws Exception {
        if (questaoMultiplaEscolha.getElaborador() == null) {
            throw new Exception("Quest�o com elaborador inv�lido!");
        } else if (questaoMultiplaEscolha.getGenero() == null) {
            throw new Exception("G�nero de quest�o inv�lido!");
        } else if (questaoMultiplaEscolha.getTexto().equals("") || questaoMultiplaEscolha.getTexto() == null) {
            throw new Exception("Quest�o vazia!");
        } else {
            dao.inserir(questaoMultiplaEscolha);
        }
    }

    public void atualizar(QuestaoMultiplaEscolha questaoMultiplaEscolha) throws Exception {

        if (questaoMultiplaEscolha.getId() <= 0) {
            throw new Exception("Id de quest�o inv�lido!");
        } else if (questaoMultiplaEscolha.getElaborador().equals("") || questaoMultiplaEscolha.getElaborador() == null) {
            throw new Exception("Quest�o com elaborador inv�lido!");
        } else if (questaoMultiplaEscolha.getGenero() == null) {
            throw new Exception("G�nero de quest�o inv�lido!");
        } else if (questaoMultiplaEscolha.getReferencia().equals("") || questaoMultiplaEscolha.getReferencia() == null) {
            throw new Exception("Quest�o sem refer�ncia v�lida!");
        } else if (questaoMultiplaEscolha.getTexto().equals("") || questaoMultiplaEscolha.getTexto() == null) {
            throw new Exception("Quest�o vazia!");
        } else {
            dao.atualizar(questaoMultiplaEscolha);
        }
    }

    public void remover(long id) throws Exception {

        if (id <= 0) {
            throw new Exception("Id de quest�o inv�lido!");
        } else {
            dao.remover(id);
        }
    }

    public QuestaoMultiplaEscolha consultarPorId(long id) throws Exception {

        return dao.consultarPorId(id);
    }

    public List<QuestaoMultiplaEscolha> consultarTodos() throws Exception {

        return dao.consultarTodos();
    }

    public List<QuestaoMultiplaEscolha> consultarTodosPorGenero(Genero genero) throws Exception {

        return dao.consultarTodosPorGenero(genero);
    }
}
