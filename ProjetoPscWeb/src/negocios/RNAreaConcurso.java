/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.AreaConcurso;
import daoImpl.AreaConcursoDAOImpl;
import factory.FactoryDAO;
import java.util.List;

/**
 *
 * @author Antônio
 */
public class RNAreaConcurso {

    AreaConcursoDAOImpl areaConcursoDAOImpl;

    public RNAreaConcurso() {
        areaConcursoDAOImpl = FactoryDAO.getAreaConcursoDAOImpl();
    }

    public void inserir(AreaConcurso areaConcurso) throws Exception {
        if (areaConcurso == null) {
            throw new Exception("Area de concurso inválida");
        } else if (areaConcurso.getNome().equals("") || areaConcurso.getNome() == null) {
            throw new Exception("Nome da Área do concurso iválido");
        } else if (areaConcurso.getFases().size() <= 0) {
            throw new Exception("Deve aver pelo menos uma fase cadastrada na área do concurso");
        }
        areaConcursoDAOImpl.inserir(areaConcurso);
    }

    public void atualizar(AreaConcurso areaConcurso) throws Exception {
        if (areaConcurso == null) {
            throw new Exception("Area de concurso inválida");
        } else if (areaConcurso.getNome().equals("") || areaConcurso.getNome() == null) {
            throw new Exception("Nome da Área do concurso iválido");
        } else if (areaConcurso.getFases().size() <= 0) {
            throw new Exception("Deve aver pelo menos uma fase cadastrada na área do concurso");
        }
        areaConcursoDAOImpl.atualizar(areaConcurso);
    }

    public void remover(long id) throws Exception {
        areaConcursoDAOImpl.remover(id);
    }
    
    public AreaConcurso consultarPorId(long id) throws Exception{
        return areaConcursoDAOImpl.consultarPorId(id);
    }

    public List<AreaConcurso> consultarTodos() throws Exception {
        return areaConcursoDAOImpl.consultarTodos();
    }
}
