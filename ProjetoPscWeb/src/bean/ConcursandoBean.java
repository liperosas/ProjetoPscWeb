package bean;

import javax.faces.bean.ManagedBean;

import classes.Concursando;
import fachada.Fachada;
import fachada.IFachada;

@ManagedBean
public class ConcursandoBean {

	private Concursando concursando;
	private String mensagem;

	private IFachada fachada = Fachada.obterInstancia();

	public ConcursandoBean() {
		concursando = new Concursando();
	}

	public String efetuarLogin() {
		try {
			concursando = fachada.logarConcrusando(concursando.getLogin(), concursando.getSenha());
			return "login.xhtml?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mensagem = e.getMessage();
		}
		return null;
	}

	public Concursando getConcursando() {
		return concursando;
	}

	public void setConcursando(Concursando concursando) {
		this.concursando = concursando;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String cadastrarConcursando() {
		try {
			fachada.inserirConcursando(concursando);
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		//return "index.xhtml?faces-redirect=true";
		return null;
	}

}
