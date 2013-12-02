package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import classes.AreaConcurso;
import classes.Concurso;
import classes.DiaFase;
import classes.Fase;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ListarConcursosBean {

	private List<Concurso> concursos;

	private List<Fase> fases;

	private IFachada fachada = Fachada.obterInstancia();

	private DataTable tableConcursos;

	private Concurso concurso;

	public ListarConcursosBean() {
		List<Concurso> con = new ArrayList<Concurso>();
		concursos = new ArrayList<Concurso>();
		fases = new ArrayList<Fase>();
		try {
			con = fachada.consultarTodosConcurso();
			for (Concurso c : con) {
				if (c.getDatainscricao().before(Calendar.getInstance())) {
					for (AreaConcurso areaConcurso : c.getAreasConcurso()) {
						fases = new ArrayList<Fase>();
						for (Fase fase : areaConcurso.getFases()) {
							int x = 0;
							Calendar calendar = Calendar.getInstance();
							for (DiaFase diaFase : fase.getDiasFase()) {
								if (diaFase.getDataDia().after(calendar)) {
									x = 1;
									break;
								}
							}
							if (x == 0) {
								fases.add(fase);
							}
						}
						areaConcurso.setFases(fases);
					}
				}
				concursos.add(c);
			}
			if (getConcursos().size() == 0) {
				FacesContext.getCurrentInstance()
						.addMessage(
								"mensagem",
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"AVISO: Não há concursos vigentes",
										"Concursos"));
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("index.xhtml?faces-redirect=true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String listarFasesConcurso() {
		concurso = (Concurso) tableConcursos.getSelection();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("concurso", concurso);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, getConcurso()
						.getNomeConcurso(), "Teste"));
		return "listarFasesConcurso.xhmtl?faces-redirect=true";
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}

	/**
	 * @param concursos
	 *            the concursos to set
	 */
	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

	private List<Fase> getFases() {
		return fases;
	}

	private void setFases(List<Fase> fases) {
		this.fases = fases;
	}

	public DataTable getTableConcursos() {
		return tableConcursos;
	}

	/**
	 * @param tableConcursos
	 *            the tableConcursos to set
	 */
	public void setTableConcursos(DataTable tableConcursos) {
		this.tableConcursos = tableConcursos;
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
}
