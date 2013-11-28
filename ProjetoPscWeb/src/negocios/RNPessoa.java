package negocios;

import java.util.Calendar;
import java.util.regex.Pattern;

import classes.Endereco;
import classes.Pessoa;

public abstract class RNPessoa {

	private char[] aCpf;

	public RNPessoa() {
		// TODO Auto-generated constructor stub
	}

	public void validarInserir(Pessoa pessoa) throws Exception {

		if (pessoa.getNome().equals("") || pessoa.getNome() == null) {
			throw new Exception("Nome inv�lido");
		} else if (validaCpf(pessoa.getCpf()) == false) {
			throw new Exception("CPF Inv�lido");
		} else if (!validaTelefone(pessoa.getTelefone())) {
			throw new Exception("Telefone inv�lido");
		} /*else if (!validaTelefone(pessoa.getCelular())) {
			throw new Exception("Celular inv�lido");
		}/* else if (pessoa.getData_nasc().YEAR < 1900
				|| pessoa.getData_nasc().YEAR > Calendar.getInstance().YEAR) {
			throw new Exception("Data inv�lida");
		}*/ else if (validarEndereco(pessoa.getEndereco())) {
			throw new Exception("Endereco inv�lido");
		}

	}

	public void validarAlterar(Pessoa pessoa) throws Exception {

		if (pessoa.getId() <= 0) {
			throw new Exception("ID Inv�lido");
		} else if (pessoa.getNome().equals("") || pessoa.getNome() == null) {
			throw new Exception("Nome inv�lido");
		} else if (validaCpf(pessoa.getCpf()) == false) {
			throw new Exception("CPF Inv�lido");
		} else if (!validaTelefone(pessoa.getTelefone())) {
			throw new Exception("Telefone inv�lido");
		} else if (!validaTelefone(pessoa.getCelular())) {
			throw new Exception("Celular inv�lido");
		}/* else if (pessoa.getData_nasc().YEAR < 1900
				|| pessoa.getData_nasc().YEAR > Calendar.getInstance().YEAR) {
			throw new Exception("Data inv�lida");
		}*/ else if (validarEndereco(pessoa.getEndereco())) {
			throw new Exception("Endereco inv�lido");
		}

	}
	
	public void validarRemover(long id) throws Exception{
		if (id <= 0){
			throw new Exception("ID inv�lido");
		}
	}	

	public boolean validaTelefone(String telefone) {
		return telefone.matches("\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{4}?");
	}

	public boolean validarEndereco(Endereco endereco) throws Exception {
		if (endereco.getBairro().equals("") || endereco.getBairro() == null) {
			throw new Exception("Bairro inv�lido");
		}/* else if (validarCEP(endereco.getCep()) == false
				|| endereco.getCep().equals("") || endereco.getCep() == null) {
			throw new Exception("Cep inv�lido");
		}*/ else if (endereco.getCidade().equals("")
				|| endereco.getCidade() == null) {
			throw new Exception("Cidade inv�lida");
		} else if (endereco.getLogradouro().equals("")
				|| endereco.getLogradouro() == null) {
			throw new Exception("Logradouro inv�lido");
		} else if (endereco.getNumero().equals("")
				|| endereco.getNumero() == null) {
			throw new Exception("N�mero inv�lido");
		} else if (endereco.getUf().equals("") || endereco.getUf() == null) {
			throw new Exception("UF inv�lido");
		}
		return false;
	}

	public boolean validarCEP(String cep) {
		return cep.matches("\\[0-9]{5}?\\-[0-9]{3}?");
	}

	public boolean validaCpf(String cpf) {
		cpf = cpf.replaceAll(Pattern.compile("\\s").toString(), "");
		cpf = cpf.replaceAll(Pattern.compile("\\D").toString(), "");

		int soma = 0;

		if (cpf.length() != 11) {
			return false;
		}

		aCpf = cpf.toCharArray();

		// Verifica 1� digito
		for (int i = 0; i < 9; i++) {
			int j = (i + 1);
			int x = Integer.parseInt(Character.toString(aCpf[i]));
			soma += (j * x);
		}

		int d1 = (soma % 11);
		if (d1 == 10) {
			d1 = 0;
		}

		// Verifica o 2� digito
		soma = 0;
		int j = 0;
		for (int i = 9; i > 0; i--) {
			int x = Integer.parseInt(Character.toString(aCpf[j]));
			soma += (i * x);
			j++;
		}

		int d2 = (soma % 11);

		if (d2 == 10) {
			d2 = 0;
		}

		if (d1 == Integer.parseInt(Character.toString(aCpf[9]))
				&& d2 == Integer.parseInt(Character.toString(aCpf[10]))) {
			return true;
		} else {
			return false;
		}
	}

}
