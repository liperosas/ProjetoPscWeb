package classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Elaborador")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Elaborador extends Pessoa {
    @Column(nullable=false)
	private String formacao;

	@OneToMany(mappedBy = "elaborador", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Questao> questoes;

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public Elaborador(int id, String nome, String cpf, String rg,
			String titulo, String telefone, String celular, Endereco endereco,
			String formacao, List<Questao> questoes) {
		super(id, nome, cpf, rg, titulo, telefone, celular, endereco);
		// TODO Auto-generated constructor stub
	}

	public Elaborador() {
		// TODO Auto-generated constructor stub
	}

}
