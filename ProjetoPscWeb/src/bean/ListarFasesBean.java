package bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import classes.AreaConcurso;
import classes.Concursando;
import classes.Concurso;
import classes.Fase;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ListarFasesBean {

	private List<Fase> fases;

	private Concurso concurso;

	private IFachada fachada = Fachada.obterInstancia();

	private FacesContext context = FacesContext.getCurrentInstance();
	
	private DataTable tableFases;

	public ListarFasesBean() {
		try{
		if (context.getExternalContext().getSessionMap().get("concurso") == null) {
			try {
				context.getExternalContext().redirect("index.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			setConcurso(((Concurso)context.getExternalContext().getSessionMap().get("concurso")));
			for (AreaConcurso areaConcurso : getConcurso().getAreasConcurso()){
				for (Fase fase : areaConcurso.getFases()){
					getFases().add(fase);
				}
			}
		}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public String listarClassificados(){
		Fase fase = (Fase) getTableFases().getSelection();
		context.getExternalContext().getSessionMap().put("fase", fase);
		return "listarClassificacao.xhtml?faces-redirect=true";
	}

	private List<Fase> getFases() {
		return fases;
	}

	
	public void setFases(List<Fase> fases) {
		this.fases = fases;
	}


	/**
	 * @return the concurso
	 */
	public Concurso getConcurso() {
		return concurso;
	}

	/**
	 * @param concurso
	 *            the concurso to set
	 */
	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	private DataTable getTableFases() {
		return tableFases;
	}
	
	/**
	 * @param tableFases
	 *            the tableFases to set
	 */
	public void setTableFases(DataTable tableFases) {
		this.tableFases = tableFases;
	}


}
