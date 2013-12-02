package classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@DiscriminatorValue("CARTRESP")
public class CartaoResposta extends RespostasProva {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_concursando", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Concursando concursando;
	
	private int corretaMultiplaEscolha;
	
	public int getCorretaMultiplaEscolha() {
		return corretaMultiplaEscolha;
	}

	public void setCorretaMultiplaEscolha(int corretaMultiplaEscolha) {
		this.corretaMultiplaEscolha = corretaMultiplaEscolha;
	}

	public int getCorretaDiscursiva() {
		return corretaDiscursiva;
	}

	public void setCorretaDiscursiva(int corretaDiscursiva) {
		this.corretaDiscursiva = corretaDiscursiva;
	}

	private int corretaDiscursiva;

	public CartaoResposta() {
		// TODO Auto-generated constructor stub
		concursando = new Concursando();
	}

	public Concursando getConcursando() {
		return concursando;
	}

	public void setConcursando(Concursando concursando) {
		this.concursando = concursando;
	}

}
