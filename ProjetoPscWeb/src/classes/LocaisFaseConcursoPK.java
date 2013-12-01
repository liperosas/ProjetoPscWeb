package classes;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LocaisFaseConcursoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="id_faseconcurso")
	private Fase fase;
	
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="id_local")
	private Local local;

	public Fase getFase() {
		return fase;
	}

	public void setAreaconcurso(Fase fase) {
		this.fase = fase;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public LocaisFaseConcursoPK(Fase fase, Local local) {
		super();
		this.fase = fase;
		this.local = local;
	}

	public LocaisFaseConcursoPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
