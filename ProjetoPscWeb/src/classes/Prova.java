package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Prova {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_genero", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Genero genero;
	@OneToMany(mappedBy = "prova", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<RespostasProva> cartoesResposta;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "questao_prova", joinColumns = @JoinColumn(name = "id_prova"), inverseJoinColumns = @JoinColumn(name = "id_questao"))
	private List<Questao> questoes;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_diafase", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private DiaFase diaFase;

	private int pesoMultipla;

	private int pesoDiscursiva;

	public int getPesoMultipla() {
		return pesoMultipla;
	}

	public void setPesoMultipla(int pesoMultipla) {
		this.pesoMultipla = pesoMultipla;
	}

	public int getPesoDiscursiva() {
		return pesoDiscursiva;
	}

	public void setPesoDiscursiva(int pesoDiscursiva) {
		this.pesoDiscursiva = pesoDiscursiva;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<RespostasProva> getCartoesResposta() {
		return cartoesResposta;
	}

	public void setCartoesResposta(List<CartaoResposta> cartoesResposta) {
		this.setCartoesResposta(cartoesResposta);
	}

	public Prova() {
		// TODO Auto-generated constructor stub
		this.genero = new Genero();
		this.cartoesResposta = new ArrayList<RespostasProva>();
		this.diaFase = new DiaFase();
		this.questoes = new ArrayList<Questao>();
	}

	/**
	 * @return the diaFase
	 */
	public DiaFase getDiaFase() {
		return diaFase;
	}

	/**
	 * @param diaFase
	 *            the diaFase to set
	 */
	public void setDiaFase(DiaFase diaFase) {
		this.diaFase = diaFase;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
}
