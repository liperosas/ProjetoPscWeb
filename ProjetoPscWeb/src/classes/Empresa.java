package classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
//import javax.persistence.CascadeType;

@Entity
public class Empresa {

	@Id @Column(name="id_empresa")
	@GeneratedValue
	private long id;
	@Column(length=18,nullable=false,unique=true)
	private String cnpj;
    @Column(length=255,nullable=false)
	private String nome;
    @Column(nullable=false)
	private String telefone;
	@Embedded
	private Endereco endereco;

	@OneToMany (mappedBy = "empresa" ,fetch = FetchType.LAZY) 
	@Cascade (CascadeType.ALL)
	private List<Concurso> concursos;

	public List<Concurso> getConcursos() {
		return concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa(long id, String cnpj, String nome, String telefone,
			Endereco endereco, List<Concurso> concursos) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.concursos = concursos;
	}

	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

}
