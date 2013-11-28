package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import classes.Concurso;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ConcursoBean {
	
	private List<Concurso> concursos;
	private Concurso selectedConcurso;
	
	public Concurso getSelectedConcurso() {
		return selectedConcurso;
	}
	public void setSelectedConcurso(Concurso selectedConcurso) {
		this.selectedConcurso = selectedConcurso;
	}

	IFachada fachada = Fachada.obterInstancia();
	
	public ConcursoBean(){
		concursos = new ArrayList<Concurso>();		
		try {
			concursos = fachada.consultarTodosConcurso();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Concurso> getConcursos() {
		return concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

}
