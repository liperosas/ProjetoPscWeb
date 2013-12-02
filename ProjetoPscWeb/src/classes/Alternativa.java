package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Alternativa {

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, columnDefinition = "longtext")
	private String texto;
	private boolean checado;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_questao", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.ALL)
	private QuestaoMultiplaEscolha questao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "alternativas_cartaoresposta_concursando",
	joinColumns = @JoinColumn(name = "id_alternativa"),
	inverseJoinColumns = @JoinColumn(name = "id_cartaoresposta"))
	private List<RespostasProva> cartoesResposta;

	public List<RespostasProva> getCartoesResposta() {
		return cartoesResposta;
	}

	public void setCartoesResposta(List<RespostasProva> cartoesResposta) {
		this.cartoesResposta = cartoesResposta;
	}

	public Alternativa(long id, String texto, boolean checado) {
		super();
		this.id = id;
		this.texto = texto;
		this.checado = checado;		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isChecado() {
		return checado;
	}

	public void setChecado(boolean checado) {
		this.checado = checado;
	}

	public Alternativa() {
		// TODO Auto-generated constructor stub
		cartoesResposta = new ArrayList<RespostasProva>();
	}

	public QuestaoMultiplaEscolha getQuestao() {
		return questao;
	}

	public void setQuestao(QuestaoMultiplaEscolha questao) {
		this.questao = questao;
	}
}
