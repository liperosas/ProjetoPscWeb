package bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean {

	  private String login;
	  private String senha;
	  private String mensagem;

	  // getters e setters

	  public String efetuarLogin(){
		if(login.equalsIgnoreCase(senha)){
		    setMensagem("Login válido");
		} else {
	  	    setMensagem("Tente novamente");
		}
		return null;
	  }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


}
