package classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table (name="Funcionario")
@PrimaryKeyJoinColumn (name="id_pessoa")
public class Funcionario extends Pessoa{
    @Column(nullable=false)
	private double salario;
	
	private String ctps;
		
	
	public double getSalario() {
		return salario;
	}



	public void setSalario(double salario) {
		this.salario = salario;
	}



	public String getCtps() {
		return ctps;
	}



	public void setCtps(String ctps) {
		this.ctps = ctps;
	}



	public Funcionario() {
		// TODO Auto-generated constructor stub
		
		
	}



	public Funcionario(int id, String nome, String cpf, String rg,
			String titulo, String telefone, String celular, Endereco endereco,
			double salario, String ctps) {
		super(id, nome, cpf, rg, titulo, telefone, celular, endereco);
		this.salario = salario;
		this.ctps = ctps;
	}
	
	

}
