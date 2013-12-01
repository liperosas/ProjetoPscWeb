package negocios;

import java.util.List;

import classes.Empresa;
import classes.Prova;
import daoImpl.ProvaDAOImpl;
import factory.FactoryDAO;

public class RNProva {

    private ProvaDAOImpl dao;

    public RNProva() {
        dao = FactoryDAO.getProvaDAOImpl();
    }

    public void inserir(Prova prova) throws Exception {
        if (prova.getGenero() == null) {
            throw new Exception("G�nero de prova inv�lido!");
        } else if (prova.getDiaFase().equals("") || prova.getDiaFase() == null) {
            throw new Exception("Fase de prova inv�lida!");
        } else if (prova.getQuestoes() == null) {
            throw new Exception("Questões inválidas");
        } else if (prova.getPesoMultipla() == 0) {
            prova.setPesoMultipla(1);
        } else if (prova.getPesoDiscursiva() == 0) {
            prova.setPesoDiscursiva(1);
        }
        dao.inserir(prova);
    }

    public void atualizar(Prova prova) throws Exception {

        if (prova.getId() <= 0) {
            throw new Exception("Id de prova inv�lido!");
        } else if (prova.getGenero().equals("") || prova.getGenero() == null) {
            throw new Exception("G�nero de prova inv�lido!");
        } else if (prova.getDiaFase() == null) {
            throw new Exception("Dia da Fase de prova inv�lida!");
        } else if (prova.getCartoesResposta().equals("") || prova.getCartoesResposta() == null) {
            throw new Exception("Cart�o Resposta inv�lido!");
        } else {
            dao.atualizar(prova);
        }
    }

    public void remover(long id) throws Exception {

        if (id <= 0) {
            throw new Exception("Id de prova inv�lido!");
        } else {
            dao.remover(id);
        }
    }

    public Prova consultarPorId(long id) throws Exception {

        return dao.consultarPorId(id);
    }

    public List<Prova> consultarTodos() throws Exception {
        return dao.consultarTodos();
    }
}
