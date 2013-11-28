package dao;

import classes.Genero;
import classes.QuestaoMultiplaEscolha;
import java.util.List;

public interface IQuestaoMultiplaDAO extends IGenericDAO<QuestaoMultiplaEscolha>{

    List<QuestaoMultiplaEscolha> consultarTodosPorGenero(Genero genero) throws Exception;
    
}
