package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;

import classes.Concurso;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ListarConcursosBean {

	private List<Concurso> concursos;

	private IFachada fachada = Fachada.obterInstancia();

	public ListarConcursosBean() {
		List<Concurso> con = new ArrayList<Concurso>();
		try {
			con = fachada.consultarTodosConcurso();
			for (Concurso c : con) {
				if (c.getDatainscricao().before(Calendar.getInstance())) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
