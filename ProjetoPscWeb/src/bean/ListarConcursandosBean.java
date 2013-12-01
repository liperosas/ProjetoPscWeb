package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import classes.CartaoResposta;
import classes.Concursando;
import classes.Fase;
import classes.Questao;
import classes.QuestaoMultiplaEscolha;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ListarConcursandosBean {

	private IFachada fachada = Fachada.obterInstancia();
	
	private List<Concursando> concursandos;
	
	private Fase fase;
	
	private double[] medias;	
	
	public ListarConcursandosBean(){
		try {
			fase = (Fase) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fase");
            fase = fachada.consultarFasePorId(fase.getId());
        } catch (Exception ex) {
        	
        }
        if (fase.isClassificacaoRealizada()){
        concursandos = new ArrayList<Concursando>();
        try {
            concursandos = fachada.calcularNotaMultiplaConcursandos(fase);
            medias = new double[concursandos.size()];
        } catch (Exception ex) {

        }
        if (!fase.isClassificacaoRealizada()) {
            fase.setClassificacaoRealizada(true);
        }
        this.classificarConcursandos();
        } else {
        	try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	public void classificarConcursandos() {
        int i = 0;
        for (Concursando concursando : concursandos) {
            double notaMultipla = 0.00;
            double notaDiscursiva = 0.00;
            double qtdProvas = 0.0;
            double notas = 0;
            for (CartaoResposta cartaoResposta : concursando.getCartoesResposta()) {
                if (cartaoResposta.getProva().getDiaFase().getFase().getId() == fase.getId()) {
                    qtdProvas++;
                    double qtdMult = 0.0;
                    double qtdDisc = 0.0;
                    for (Questao questao : cartaoResposta.getProva().getQuestoes()) {
                        if (questao instanceof QuestaoMultiplaEscolha) {
                            qtdMult++;
                        } else {
                            qtdDisc++;
                        }
                    }
                    notaMultipla = (cartaoResposta.getCorretaMultiplaEscolha() * cartaoResposta.getProva().getPesoMultipla()) / qtdMult;
                    notaDiscursiva = (cartaoResposta.getCorretaDiscursiva() * cartaoResposta.getProva().getPesoDiscursiva()) / qtdDisc;
                    notas += notaDiscursiva + notaMultipla;
                }
            }
            medias[i] = notas / qtdProvas;
            i++;


        }
        for (int j = 0; j < medias.length; j++) {
            for (int x = 0; x < medias.length; x++) {
                if (medias[j] > medias[x]) {
                    double media2 = medias[j];
                    medias[j] = medias[x];
                    medias[x] = media2;
                    Concursando c = new Concursando();
                    c = concursandos.get(j);
                    concursandos.set(j, concursandos.get(x));
                    concursandos.set(x, c);
                }
            }
        }
    }

	/**
	 * @return the fachada
	 */
	public IFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(IFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the concursandos
	 */
	public List<Concursando> getConcursandos() {
		return concursandos;
	}

	/**
	 * @param concursandos the concursandos to set
	 */
	public void setConcursandos(List<Concursando> concursandos) {
		this.concursandos = concursandos;
	}

	/**
	 * @return the fase
	 */
	public Fase getFase() {
		return fase;
	}

	/**
	 * @param fase the fase to set
	 */
	public void setFase(Fase fase) {
		this.fase = fase;
	}

	/**
	 * @return the medias
	 */
	public double[] getMedias() {
		return medias;
	}

	/**
	 * @param medias the medias to set
	 */
	public void setMedias(double[] medias) {
		this.medias = medias;
	}
	
	
	
}
