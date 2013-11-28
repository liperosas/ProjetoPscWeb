package classes;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Local {

	@Id @GeneratedValue
	private long id;
	
	private boolean disponivel;
	
	@Embedded
	private Endereco endereco;
	
        private String nome;
	/*@OneToMany (mappedBy="local", fetch = FetchType.LAZY)
	@Cascade (CascadeType.ALL)
	private List<LocaisAreaConcurso> locaisareaconcurso;*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/*public List<LocaisAreaConcurso> getConcursos() {
		return locaisareaconcurso;
	}

	public void setConcursos(List<LocaisAreaConcurso> locaisareaconcurso) {
		this.locaisareaconcurso = locaisareaconcurso;
	}
*/
	public Local(int id, boolean disponivel, Endereco endereco,
			List<LocaisFaseConcurso> locaisareaconcurso, String nome) {
		super();
		this.id = id;
		this.disponivel = disponivel;
		this.endereco = endereco;
                this.nome = nome;
		//this.locaisareaconcurso = locaisareaconcurso;
	}

	public Local() {
		super();
		// TODO Auto-generated constructor stub
	}

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
	
	
}
