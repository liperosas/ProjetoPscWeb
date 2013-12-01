package junit;

import static org.junit.Assert.assertArrayEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import classes.Elaborador;
import classes.Endereco;
import daoImpl.ElaboradorDAOImpl;
import fachada.Fachada;
import fachada.IFachada;
import factory.FactoryDAO;

public class ElaboradorTeste extends TestCase {

	IFachada fachada = Fachada.obterInstancia();

	public void testeAlterarElaborador() {
		boolean resultado = true;
		Elaborador elaborador = new Elaborador();
		elaborador.setId(1);
		elaborador.setNome("TRE");
		elaborador.setCpf("647.889.168-27");
		elaborador.setRg("7.999.999");
		elaborador.setFormacao("Graduado");
		elaborador.setTelefone("(81)8888-8888");
		elaborador.setCelular("(81)8888-8888");
		Endereco endereco = new Endereco();
		endereco.setUf("PE");
		endereco.setCidade("Recife");
		endereco.setBairro("campo grande");
		endereco.setCep("50550-440");
		endereco.setLogradouro("Rua DR.Machado");
		endereco.setNumero("'643");
		endereco.setComplemento("b");
		elaborador.setEndereco(endereco);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			elaborador.getData_nasc().setTime(dateFormat.parse("12/11/2013"));
			fachada.atualizarElaborador(elaborador);
		} catch (Exception e) {
			resultado = false;
			e.printStackTrace();
		}
		assertTrue("Resultado ", resultado);

	}

	public void testeInserirElaborador() {
		boolean resultado = true;

		Elaborador elaborador = new Elaborador();
		elaborador.setNome("TRE");
		elaborador.setCpf("253.213.792-46");
		elaborador.setRg("7.999.998");
		elaborador.setFormacao("Graduado");
		elaborador.setTelefone("(81)8888-8888");
		Endereco endereco = new Endereco();
		endereco.setUf("PE");
		endereco.setCidade("Recife");
		endereco.setBairro("campo grande");
		endereco.setCep("50550-440");
		endereco.setLogradouro("Rua DR.Machado");
		endereco.setNumero("'643");
		endereco.setComplemento("b");
		elaborador.setEndereco(endereco);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			elaborador.getData_nasc().setTime(dateFormat.parse("12/11/2013"));
			fachada.inserirElaborador(elaborador);
		} catch (Exception e) {
			resultado = false;
			e.printStackTrace();
		}
		assertTrue(resultado);

	}

	public void testeConsultarTodosElaborador() {
		try {
			ElaboradorDAOImpl dao = FactoryDAO.getElaboradorDAOImpl();
			List<Elaborador> elaborador = fachada.consultarTodosElaborador();
			List<Elaborador> elaboradoresperado = dao.consultarTodos();
			assertArrayEquals(elaboradoresperado.toArray(),
					elaborador.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
