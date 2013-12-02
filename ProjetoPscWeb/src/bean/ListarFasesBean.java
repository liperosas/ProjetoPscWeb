package bean;

import java.io.IOException;
import java.util.ArrayList;
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

	private Fase fase;

	public ListarFasesBean() {
		try {
			fases = new ArrayList<Fase>();
			if (context.getExternalContext().getSessionMap().get("concurso") == null) {
				try {
					context.getExternalContext().redirect("index.xhtml?faces-redirect=true");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				setConcurso(((Concurso) context.getExternalContext()
						.getSessionMap().get("concurso")));
				for (AreaConcurso areaConcurso : getConcurso()
						.getAreasConcurso()) {
					for (Fase fase : areaConcurso.getFases()) {
						getFases().add(fase);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String listarClassificados() {
		setFase((Fase) tableFases.getSelection());
		context.getExternalContext().getSessionMap().put("fase", getFase());
		return "listarClassificacao.xhtml?faces-redirect=true";
	}

	public List<Fase> getFases() {
		return fases;
	}

	/**
	 * @return the fases
	 */
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

	public DataTable getTableFases() {
		return tableFases;
	}

	/**
	 * @param tableConcursos
	 *            the tableConcursos to set
	 */
	public void setTableFases(DataTable tableFases) {
		this.tableFases = tableFases;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

}
