package bean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import classes.AreaConcurso;
import classes.Concursando;
import classes.DiaFase;
import classes.Fase;

@ManagedBean
public class ListarFasesAreaConcurso {

	private List<Fase> fases;

	private FacesContext context = FacesContext.getCurrentInstance();

	private Concursando concursando;

	TreeNode rootConcursando;

	public ListarFasesAreaConcurso() {
		if (context.getExternalContext().getSessionMap().get("concursando") == null) {
			try {
				context.getExternalContext().redirect(
						"index.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			concursando = (Concursando) context.getExternalContext()
					.getSessionMap().get("concursando");
			rootConcursando = new DefaultTreeNode(concursando.getNome(), null);
			for (AreaConcurso areaConcurso : concursando.getAreasconcurso()) {
				TreeNode nodeAc = new DefaultTreeNode(areaConcurso.getNome(),
						rootConcursando);
				int fases = 0;
				for (Fase fase : areaConcurso.getFases()) {
					fases++;
					TreeNode nodeFase = new DefaultTreeNode(fases + "ª Fase",
							nodeAc);
					for (DiaFase diafase : fase.getDiasFase()) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd/MM/yyyy");
						SimpleDateFormat hora = new SimpleDateFormat(
								"hh:mm");
						TreeNode nodeDiaFase = new DefaultTreeNode(
								sdf.format(diafase.getDataDia().getTime()) + " Hora Inicial: " + hora.format(diafase.getHoraInicial().getTime())  + " Hora Final: " + hora.format(diafase.getHoraFinal().getTime()),
								nodeFase);
					}
				}
			}
		}
	}

	public TreeNode getRootConcursando() {
		return rootConcursando;
	}

}
