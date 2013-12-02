package dao;

import java.util.List;

import classes.Gabarito;

public interface IGabaritoDAO extends IGenericDAO<Gabarito>{

    List<Gabarito> consultarGabaritoProva(long id_prova) throws Exception;
    
}
