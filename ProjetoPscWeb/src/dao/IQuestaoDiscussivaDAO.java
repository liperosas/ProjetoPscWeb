package dao;

import java.util.List;

import classes.Genero;
import classes.QuestaoDiscursiva;

public interface IQuestaoDiscussivaDAO extends IGenericDAO<QuestaoDiscursiva> {

    List<QuestaoDiscursiva> consultarTodosPorGenero(Genero genero) throws Exception;
}
