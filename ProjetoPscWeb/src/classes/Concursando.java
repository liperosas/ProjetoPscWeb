package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Concursando extends Pessoa {

	@Column(length = 18, nullable = false)
	private String senha;
	@Column(length = 100, nullable = false)
	private String login;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "areas_concurso_concursando", joinColumns = @JoinColumn(name = "id_concursando"), inverseJoinColumns = @JoinColumn(name = "id_area_concurso"))
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<AreaConcurso> areasconcurso;

	@OneToMany(mappedBy = "concursando", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<CartaoResposta> cartoesResposta;

	public Concursando(int id, String nome, String cpf, String rg,
			String titulo, String telefone, String celular, Endereco endereco,
			Concurso concurso) {
		super(id, nome, cpf, rg, titulo, telefone, celular, endereco);
		// TODO Auto-generated constructor stub
	}

	public Concursando() {
		// TODO Auto-generated constructor stub
		areasconcurso = new ArrayList<AreaConcurso>();
		cartoesResposta = new ArrayList<CartaoResposta>();
	}

	public List<CartaoResposta> getCartoesResposta() {
		return cartoesResposta;
	}

	public void setCartoesResposta(List<CartaoResposta> cartoesResposta) {
		this.cartoesResposta = cartoesResposta;
	}

	public List<AreaConcurso> getAreasconcurso() {
		return areasconcurso;
	}

	public void setAreasconcurso(List<AreaConcurso> areasconcurso) {
		this.areasconcurso = areasconcurso;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
