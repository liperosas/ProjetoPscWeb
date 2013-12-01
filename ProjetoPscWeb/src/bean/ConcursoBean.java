package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import classes.Concurso;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ConcursoBean {

	List<Concurso> concursos;
	
	Concurso concurso;
	
	DataTable tableConcursos;

	IFachada fachada = Fachada.obterInstancia();

	public ConcursoBean() {
		concursos = new ArrayList<Concurso>();
		List<Concurso> c = new ArrayList<Concurso>();
		try {
			c = fachada.consultarTodosConcurso();
			for (Concurso con : c)
			{
				if (con.getDatainscricao().after(Calendar.getInstance()) && con.getDatafinal().before(Calendar.getInstance())){
					concursos.add(con);
				}
			}
			if (concursos.size() <= 0){
				FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN,"AVISO: Não há concursos vigentes","Concursos"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String listarAreasConcurso(){
		concurso = (Concurso) tableConcursos.getSelection();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("concurso", concurso);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, concurso.getNomeConcurso(), "Teste"));
		return "listarAreasConcurso.xhmtl?faces-redirect=true";		
	}
	
	/**
	 * @return the concursos
	 */
	public List<Concurso> getConcursos() {
		return concursos;
	}

	/**
	 * @param concursos the concursos to set
	 */
	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

	/**
	 * @return the concurso
	 */
	public Concurso getConcurso() {
		return concurso;
	}

	/**
	 * @param concurso the concurso to set
	 */
	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	/**
	 * @return the tableConcursos
	 */
	public DataTable getTableConcursos() {
		return tableConcursos;
	}

	/**
	 * @param tableConcursos the tableConcursos to set
	 */
	public void setTableConcursos(DataTable tableConcursos) {
		this.tableConcursos = tableConcursos;
	}

}
