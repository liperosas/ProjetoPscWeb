package classes;

import java.util.Calendar;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locais_faseconcurso")
public class LocaisFaseConcurso {

	@EmbeddedId
	private LocaisFaseConcursoPK chaveComposta;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	public LocaisFaseConcursoPK getChaveComposta() {
		return chaveComposta;
	}

	public void setChaveComposta(LocaisFaseConcursoPK chaveComposta) {
		this.chaveComposta = chaveComposta;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public LocaisFaseConcurso(LocaisFaseConcursoPK chaveComposta, Calendar data) {
		super();
		//this.chaveComposta = chaveComposta;
		this.data = data;
	}

	public LocaisFaseConcurso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
