package bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import classes.AreaConcurso;
import classes.Concursando;
import classes.Concurso;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class AreaConcursoBean {

	private List<AreaConcurso> areasConcurso;

	private AreaConcurso areaConcurso;

	private DataTable tableAreasConcurso;

	private IFachada fachada = Fachada.obterInstancia();

	public Concurso concurso;

	public String mensagem;

	public AreaConcursoBean() {
		if (!FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("concurso").equals(null)) {
			try {
				concurso = (Concurso) FacesContext.getCurrentInstance()
						.getExternalContext().getSessionMap().get("concurso");
				areasConcurso = concurso.getAreasConcurso();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the areasConcurso
	 */
	public List<AreaConcurso> getAreasConcurso() {
		return areasConcurso;
	}

	/**
	 * @param areasConcurso
	 *            the areasConcurso to set
	 */
	public void setAreasConcurso(List<AreaConcurso> areasConcurso) {
		this.areasConcurso = areasConcurso;
	}

	/**
	 * @return the areaConcurso
	 */
	public AreaConcurso getAreaConcurso() {
		return areaConcurso;
	}

	/**
	 * @param areaConcurso
	 *            the areaConcurso to set
	 */
	public void setAreaConcurso(AreaConcurso areaConcurso) {
		this.areaConcurso = areaConcurso;
	}

	/**
	 * @return the dataTable
	 */
	public DataTable getDataTable() {
		return tableAreasConcurso;
	}

	/**
	 * @param dataTable
	 *            the dataTable to set
	 */
	public void setDataTable(DataTable dataTable) {
		this.tableAreasConcurso = dataTable;
	}

	/**
	 * @return the tableAreasConcurso
	 */
	public DataTable getTableAreasConcurso() {
		return tableAreasConcurso;
	}

	/**
	 * @param tableAreasConcurso
	 *            the tableAreasConcurso to set
	 */
	public void setTableAreasConcurso(DataTable tableAreasConcurso) {
		this.tableAreasConcurso = tableAreasConcurso;
	}

	/**
	 * @return the fachada
	 */
	public IFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada
	 *            the fachada to set
	 */
	public void setFachada(IFachada fachada) {
		this.fachada = fachada;
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

	public String cadastrarConcursandoArea() {
		try {
			if (FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get("concursando") == null) {
				return "index.xhtml?faces-redirect=true";
			} else {
				Concursando concursando = new Concursando();
				concursando = (Concursando) FacesContext.getCurrentInstance()
						.getExternalContext().getSessionMap()
						.get("concursando");
				AreaConcurso ac = new AreaConcurso();
				ac = (AreaConcurso) tableAreasConcurso.getSelection();
				for (AreaConcurso areaC : concursando.getAreasconcurso()){
					if (ac.getConcurso().getId() == areaC.getConcurso().getId()){
						concursando.getAreasconcurso().remove(areaC);
						break;
					}
				}
				concursando.getAreasconcurso().add(ac);
				fachada.atualizarConcursando(concursando);
				return "areaCadastrada.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mensagem = e.getMessage();
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
