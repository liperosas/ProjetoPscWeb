package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import classes.Concursando;
import classes.Fase;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ListarFasesBean {

	private List<Fase> fases;
	
	private Concursando concursando;
	
	private IFachada fachada = Fachada.obterInstancia();
	
	public ListarFasesBean(){
		
	}
	
}
