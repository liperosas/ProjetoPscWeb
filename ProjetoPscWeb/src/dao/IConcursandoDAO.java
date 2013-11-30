package dao;

import java.util.List;

import classes.Concursando;
import classes.Fase;


public interface IConcursandoDAO extends IGenericDAO<Concursando>{

	Concursando logarConcursando(String Login, String senha);
	
	List<Concursando> calcularNotaMultiplaConcursandos(Fase fase) throws Exception;
	
}
