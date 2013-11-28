package dao;

import classes.Genero;
import classes.QuestaoDiscursiva;
import java.util.List;

public interface IQuestaoDiscussivaDAO extends IGenericDAO<QuestaoDiscursiva> {

    List<QuestaoDiscursiva> consultarTodosPorGenero(Genero genero) throws Exception;
}
