package dao;

import java.util.List;

import classes.Genero;
import classes.QuestaoMultiplaEscolha;

public interface IQuestaoMultiplaDAO extends IGenericDAO<QuestaoMultiplaEscolha>{

    List<QuestaoMultiplaEscolha> consultarTodosPorGenero(Genero genero) throws Exception;
    
}
