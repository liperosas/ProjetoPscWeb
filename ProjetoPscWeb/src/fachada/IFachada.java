package fachada;

import classes.Alternativa;
import classes.AreaConcurso;
import classes.CartaoResposta;

import java.util.List;

import classes.Concursando;
import classes.Concurso;
import classes.DiaFase;
import classes.Elaborador;
import classes.Funcionario;
import classes.Empresa;
import classes.Fase;
import classes.Gabarito;
import classes.Genero;
import classes.Local;
import classes.Prova;
import classes.QuestaoDiscursiva;
import classes.QuestaoMultiplaEscolha;

public interface IFachada {

	void inserirFuncionario(Funcionario funcionario) throws Exception;

	void atualizarFuncionario(Funcionario funcionario) throws Exception;

	void removerFuncionario(long id) throws Exception;

	Funcionario consultarFuncionarioPorId(long id) throws Exception;

	List<Funcionario> consultarTodosFuncionarios() throws Exception;

	void inserirEmpresa(Empresa empresa) throws Exception;

	void atualizarEmpresa(Empresa empresa) throws Exception;

	void removerEmpresa(long id) throws Exception;

	Empresa consultarEmpresaPorId(long id) throws Exception;

	List<Empresa> consultarTodosEmpresa() throws Exception;

	void inserirConcursando(Concursando concursando) throws Exception;

	void atualizarConcursando(Concursando concursando) throws Exception;

	void removerConcursando(long id) throws Exception;

	Concursando consultarConcursandoPorId(long id) throws Exception;

	List<Concursando> consultarTodosConcursando() throws Exception;

	Concursando logarConcrusando(String login, String senha) throws Exception;

	List<Concursando> calcularNotaMultiplaConcursandos(Fase fase) throws Exception;
        
        List<CartaoResposta> consultarCartoesRespostaConcursandoProva(Prova prova, Concursando concursando) throws Exception;

	void inserirProva(Prova prova) throws Exception;

	void atualizarProva(Prova prova) throws Exception;

	void removerProva(long id) throws Exception;

	Prova consultarProvaPorId(long id) throws Exception;

	List<Prova> consultarTodosProva() throws Exception;

	void inserirQuestaoDiscursiva(QuestaoDiscursiva questaoDiscursiva)
			throws Exception;

	void atualizarQuestaoDiscursiva(QuestaoDiscursiva questaoDiscursiva)
			throws Exception;

	void removerQuestaoDiscursiva(long id) throws Exception;

	QuestaoDiscursiva consultarQuestaoDiscursivaPorId(long id) throws Exception;

	List<QuestaoDiscursiva> consultarTodosQuestaoDiscursiva() throws Exception;

	List<QuestaoDiscursiva> consultarTodosPorGeneroQuestaoDiscursiva(
			Genero genero) throws Exception;

	void inserirQuestaoMultiplaEscolha(
			QuestaoMultiplaEscolha questaoMultiplaEscolha) throws Exception;

	void atualizarQuestaoMultiplaEscolha(
			QuestaoMultiplaEscolha questaoMultiplaEscolha) throws Exception;

	void removerQuestaoMultiplaEscolha(long id) throws Exception;

	QuestaoMultiplaEscolha consultarQuestaoMultiplaEscolhaPorId(long id)
			throws Exception;

	List<QuestaoMultiplaEscolha> consultarTodosQuestaoMultiplaEscolha()
			throws Exception;

	List<QuestaoMultiplaEscolha> consultarTodosPorGeneroQuestaoMultiplaEscolha(
			Genero genero) throws Exception;

	void inserirElaborador(Elaborador elaborador) throws Exception;

	void atualizarElaborador(Elaborador elaborador) throws Exception;

	void removerElaborador(long id) throws Exception;

	Elaborador consultarElaboradorPorId(long id) throws Exception;

	List<Elaborador> consultarTodosElaborador() throws Exception;

	Alternativa inserirAlternativaQuestao(Alternativa alternativa)
			throws Exception;

	void inserirAlternativa(Alternativa alternativa) throws Exception;

	void atualizarAlternativa(Alternativa alternativa) throws Exception;

	void removerAlternativa(long id) throws Exception;

	Alternativa consultarAlternativaPorId(long id) throws Exception;

	List<Alternativa> consultarTodosAlternativa() throws Exception;

	void inserirDiaFase(DiaFase diaFase) throws Exception;

	void removerDiaFase(long id) throws Exception;

	void atualizarDiaFase(DiaFase diaFase) throws Exception;

	DiaFase consultarDiaFasePorId(long id) throws Exception;

	void inserirFase(Fase fase) throws Exception;

	void removerFase(long id) throws Exception;

	void atualizarFase(Fase fase) throws Exception;

	Fase consultarFasePorId(long id) throws Exception;

	void inserirGenero(Genero genero) throws Exception;

	void atualizarGenero(Genero genero) throws Exception;

	void removerGenero(long id) throws Exception;

	Genero consultarGeneroPorId(long id) throws Exception;

	List<Genero> consultarTodosGenero() throws Exception;

	void inserirConcurso(Concurso concurso) throws Exception;

	void atualizarConcurso(Concurso concurso) throws Exception;

	void removerConcurso(long id) throws Exception;

	List<Concurso> consultarTodosConcurso() throws Exception;

	Concurso consultarConcursoPorId(long id) throws Exception;

	void inserirAreaConcurso(AreaConcurso areaconcurso) throws Exception;

	void atualizarAreaConcurso(AreaConcurso areaconcurso) throws Exception;

	void removerAreaConcurso(long id) throws Exception;

	AreaConcurso consultarAreaConcursoPorId(long id) throws Exception;

	List<AreaConcurso> consultarTodosAreaConcurso() throws Exception;

	void inserirCartaoResposta(CartaoResposta cartaoResposta) throws Exception;

	void atualizarCartaoResposta(CartaoResposta cartaoResposta)
			throws Exception;

	void removerCartaoResposta(long id) throws Exception;

	CartaoResposta consultarCartaoRespostaPorId(long id) throws Exception;

	List<CartaoResposta> consultarTodosCartaoResposta() throws Exception;

	void inserirLocal(Local local) throws Exception;

	void atualizarLocal(Local local) throws Exception;

	void removerLocal(long id) throws Exception;

	Local consultarLocalPorId(long id) throws Exception;

	List<Local> consultarTodosLocal() throws Exception;
        
        void inserirGabarito(Gabarito gabarito) throws Exception;

	void atualizarGabarito(Gabarito gabarito) throws Exception;

	void removerGabarito(long id) throws Exception;

	Gabarito consultarGabaritoPorId(long id) throws Exception;

	List<Gabarito> consultarTodosGabarito() throws Exception;
        
        List<Gabarito> consultarGabaritoProva(long id_prova) throws Exception;
}
