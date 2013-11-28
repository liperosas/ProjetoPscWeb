package dao;

import classes.Gabarito;
import java.util.List;

public interface IGabaritoDAO extends IGenericDAO<Gabarito>{

    List<Gabarito> consultarGabaritoProva(long id_prova) throws Exception;
    
}
