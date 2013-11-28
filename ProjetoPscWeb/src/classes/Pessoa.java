package classes;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table (name = "Pessoa")
public abstract class Pessoa {
	
	@Id
	@GeneratedValue
	@Column (name = "id_pessoa")
	private long id;
	@Column(length=100,nullable=false)
	private String nome;
	@Column(length=14,nullable=false,unique=true)
	private String cpf;
	@Column(length=9,nullable=false,unique=true)
	private String rg;
	@Column(length=12,nullable=true,unique=true)
	private String titulo;
	@Column(nullable=false)
	private String telefone;
	@Column(nullable=true)
	private String celular;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar data_nasc;
	
	@Embedded
	private Endereco endereco;	
	/*private String email;
	private String login;
	private String senha;*/
		
	public Pessoa(long id, String nome, String cpf, String rg, String titulo,
			String telefone, String celular, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.titulo = titulo;
		this.telefone = telefone;
		this.celular = celular;
		this.endereco = endereco;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getRg() {
		return rg;
	}



	public void setRg(String rg) {
		this.rg = rg;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa() {
		// TODO Auto-generated constructor stub
		endereco = new Endereco();
		data_nasc= Calendar.getInstance();
	}

	public Calendar getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Calendar data_nasc) {
		this.data_nasc = data_nasc;
	}

}
