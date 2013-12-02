package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class RespostasProva {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_prova", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Prova prova;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "alternativas_cartaoresposta_concursando", joinColumns = @JoinColumn(name = "id_cartaoresposta"), inverseJoinColumns = @JoinColumn(name = "id_alternativa"))
	private List<Alternativa> alternativas;

	public RespostasProva() {
		// TODO Auto-generated constructor stub
		prova = new Prova();
		alternativas = new ArrayList<Alternativa>();
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

}
