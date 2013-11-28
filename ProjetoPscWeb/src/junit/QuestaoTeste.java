package junit;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import classes.Alternativa;
import classes.QuestaoDiscursiva;
import classes.QuestaoMultiplaEscolha;
import daoImpl.QuestaoDiscursivaDAOImpl;
import daoImpl.QuestaoMultiplaDAOImpl;
import fachada.Fachada;
import fachada.IFachada;
import factory.FactoryDAO;
import gui.CRUDQuestao;

public class QuestaoTeste extends TestCase {

	CRUDQuestao crudQuestao;
	IFachada fachada = Fachada.obterInstancia();

	public void testeAlterarQuestao() {
		QuestaoDiscursiva qDiscursiva = new QuestaoDiscursiva();
		qDiscursiva.setId(2);
		qDiscursiva.getElaborador().setId(1);
		qDiscursiva.getGenero().setId(1);
		qDiscursiva.setLinhas(4);
		qDiscursiva.setResposta("Pessoal");
		qDiscursiva.setTexto("Teste JUnit");
		qDiscursiva.setReferencia("JUnit");
		boolean resultado = true;
		try {
			fachada.atualizarQuestaoDiscursiva(qDiscursiva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultado = false;
			e.printStackTrace();
		}
		assertTrue("Resultado ", resultado);
	}

	public void testeConsultarQuestaoPorId() {
		try {
			QuestaoDiscursiva qdDiscursiva = fachada
					.consultarQuestaoDiscursivaPorId(2);
			assertNotNull(qdDiscursiva);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testeConsultarTodosQuestao() {
		try {
			QuestaoDiscursivaDAOImpl dao = FactoryDAO
					.getQuestaoDiscussivaDAOImpl();
			List<QuestaoDiscursiva> qDiscursivas = fachada
					.consultarTodosQuestaoDiscursiva();
			List<QuestaoDiscursiva> qDiscursivasEsperada = dao.consultarTodos();
			assertArrayEquals(qDiscursivasEsperada.toArray(),
					qDiscursivas.toArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testeInserirQuestaoDiscursiva() {
		QuestaoDiscursiva qDiscursiva = new QuestaoDiscursiva();
		qDiscursiva.getElaborador().setId(1);
		qDiscursiva.getGenero().setId(1);
		qDiscursiva.setLinhas(4);
		qDiscursiva.setResposta("Pessoal");
		qDiscursiva.setTexto("Teste JUnit");
		qDiscursiva.setReferencia("JUnit");
		boolean resultado = true;
		try {
			fachada.inserirQuestaoDiscursiva(qDiscursiva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultado = false;
			e.printStackTrace();
		}
		assertTrue(resultado);
	}

	public void testeAlterarQuestaoMultipla() {
		QuestaoMultiplaEscolha qMultiplaEscolha = new QuestaoMultiplaEscolha();
		qMultiplaEscolha.setId(1);
		qMultiplaEscolha.getElaborador().setId(1);
		qMultiplaEscolha.getGenero().setId(1);
		qMultiplaEscolha.setTexto("Teste JUnit");
		qMultiplaEscolha.setReferencia("JUnit");
		for (int i = 0; i < 6; i++) {
			Alternativa alternativa = new Alternativa();
			alternativa.setTexto("Teste");
			alternativa.setQuestao(qMultiplaEscolha);
			qMultiplaEscolha.getAlternativas().add(alternativa);
		}
		boolean resultado = true;

		try {
			fachada.atualizarQuestaoMultiplaEscolha(qMultiplaEscolha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultado = false;
			e.printStackTrace();
		}
		assertTrue("Resultado ", resultado);
	}

	public void testeConsultarQuestaoMultiplaPorId() {
		try {
			QuestaoMultiplaEscolha qdMultiplaEscolha = fachada
					.consultarQuestaoMultiplaEscolhaPorId(1);
			assertNotNull(qdMultiplaEscolha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testeConsultarTodosQuestaoMultipla() {
		try {
			QuestaoMultiplaDAOImpl dao = FactoryDAO.getQuestaoMultiplaDAOImpl();
			List<QuestaoMultiplaEscolha> qMultiplas = fachada
					.consultarTodosQuestaoMultiplaEscolha();
			List<QuestaoMultiplaEscolha> qMultiplasEsperada = dao
					.consultarTodos();
			assertArrayEquals(qMultiplasEsperada.toArray(),
					qMultiplas.toArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testeInserirQuestaoMultipla() {
		QuestaoMultiplaEscolha qMultiplaEscolha = new QuestaoMultiplaEscolha();
		qMultiplaEscolha.getElaborador().setId(1);
		qMultiplaEscolha.getGenero().setId(1);
		qMultiplaEscolha.setTexto("Teste JUnit");
		qMultiplaEscolha.setReferencia("JUnit");
		for (int x = 0; x < 6; x++) {
			Alternativa alternativa = new Alternativa();
			alternativa.setTexto("Teste");
			alternativa.setQuestao(qMultiplaEscolha);
			qMultiplaEscolha.getAlternativas().add(alternativa);
		}
		boolean resultado = true;
		try {
			fachada.inserirQuestaoMultiplaEscolha(qMultiplaEscolha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultado = false;
			e.printStackTrace();
		}
		assertTrue(resultado);
	}

	

}
