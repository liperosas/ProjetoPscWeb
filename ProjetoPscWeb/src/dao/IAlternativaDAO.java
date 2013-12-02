package dao;

import classes.Alternativa;


public interface IAlternativaDAO extends IGenericDAO<Alternativa>{

    Alternativa inserirAlternativa(Alternativa entidade) throws Exception;
    
}
