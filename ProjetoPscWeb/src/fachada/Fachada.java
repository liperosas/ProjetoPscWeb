package fachada;

import classes.Alternativa;
import classes.AreaConcurso;
import classes.CartaoResposta;
import classes.Concursando;
import classes.Concurso;
import classes.DiaFase;
import classes.Elaborador;
import classes.Empresa;
import classes.Fase;
import classes.Funcionario;
import classes.Gabarito;
import classes.Genero;
import classes.Local;
import classes.Prova;
import classes.QuestaoDiscursiva;
import classes.QuestaoMultiplaEscolha;

import java.util.List;

import negocios.RNAlternativa;
import negocios.RNAreaConcurso;
import negocios.RNCartaoResposta;
import negocios.RNConcursando;
import negocios.RNConcurso;
import negocios.RNDiaFase;
import negocios.RNElaborador;
import negocios.RNEmpresa;
import negocios.RNFase;
import negocios.RNFuncionario;
import negocios.RNGabarito;
import negocios.RNGenero;
import negocios.RNLocal;
import negocios.RNProva;
import negocios.RNQuestaoDiscursiva;
import negocios.RNQuestaoMultipla;

public class Fachada implements IFachada {

    private static Fachada instancia;
    private RNFuncionario rnFuncionario;
    private RNEmpresa rnEmpresa;
    private RNConcursando rnConcursando;
    private RNProva rnProva;
    private RNQuestaoDiscursiva rnQuestaoDiscursiva;
    private RNQuestaoMultipla rnQuestaoMultiplaEscolha;
    private RNAlternativa rnAlternativa;
    private RNElaborador rnElaborador;
    private RNDiaFase rnDiaFase;
    private RNFase rnFase;
    private RNGenero rnGenero;
    private RNConcurso rnConcurso;
    private RNAreaConcurso rnAreaConcurso;
    private RNCartaoResposta rnCartaoResposta;
    private RNLocal rnLocal;
    private RNGabarito rnGabarito;

    public static Fachada obterInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    private Fachada() {
        rnFuncionario = new RNFuncionario();
        rnEmpresa = new RNEmpresa();
        rnConcursando = new RNConcursando();
        rnAlternativa = new RNAlternativa();
        rnElaborador = new RNElaborador();
        rnQuestaoMultiplaEscolha = new RNQuestaoMultipla();
        rnQuestaoDiscursiva = new RNQuestaoDiscursiva();
        rnDiaFase = new RNDiaFase();
        rnFase = new RNFase();
        rnGenero = new RNGenero();
        rnConcurso = new RNConcurso();
        rnAreaConcurso = new RNAreaConcurso();
        rnProva = new RNProva();
        rnCartaoResposta = new RNCartaoResposta();
        rnLocal = new RNLocal();
        rnGabarito = new RNGabarito();
    }

    @Override
    public void inserirFuncionario(Funcionario funcionario) throws Exception {
        // TODO Auto-generated method stub
        rnFuncionario.inserir(funcionario);
    }

    @Override
    public void atualizarFuncionario(Funcionario funcionario) throws Exception {
        // TODO Auto-generated method stub
        rnFuncionario.atualizar(funcionario);
    }

    @Override
    public void removerFuncionario(long id) throws Exception {
        // TODO Auto-generated method stub
        rnFuncionario.remover(id);
    }

    @Override
    public Funcionario consultarFuncionarioPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        return rnFuncionario.consultarPorId(id);
    }

    @Override
    public List<Funcionario> consultarTodosFuncionarios() throws Exception {
        // TODO Auto-generated method stub
        return rnFuncionario.consultarTodos();
    }

    @Override
    public void inserirEmpresa(Empresa empresa) throws Exception {
        rnEmpresa.inserir(empresa);
    }

    @Override
    public void atualizarEmpresa(Empresa empresa) throws Exception {
        rnEmpresa.atualizar(empresa);
    }

    @Override
    public void removerEmpresa(long id) throws Exception {
        rnEmpresa.remover(id);
    }

    @Override
    public Empresa consultarEmpresaPorId(long id) throws Exception {
        return rnEmpresa.consultarPorId(id);
    }

    @Override
    public List<Empresa> consultarTodosEmpresa() throws Exception {
        return rnEmpresa.consultarTodos();
    }

    @Override
    public void inserirConcursando(Concursando concursando) throws Exception {
        // TODO Auto-generated method stub
        rnConcursando.inserir(concursando);
    }

    @Override
    public void atualizarConcursando(Concursando concursando) throws Exception {
        // TODO Auto-generated method stub
        rnConcursando.atualizar(concursando);
    }

    @Override
    public void removerConcursando(long id) throws Exception {
        // TODO Auto-generated method stub
        rnConcursando.remover(id);
    }

    @Override
    public Concursando consultarConcursandoPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        return rnConcursando.consultarPorId(id);
    }

    @Override
    public List<Concursando> consultarTodosConcursando() throws Exception {
        // TODO Auto-generated method stub
        return rnConcursando.consultarTodos();
    }

    @Override
    public Concursando logarConcrusando(String login, String senha)
            throws Exception {
        return rnConcursando.logarConcursando(login, senha);
    }

    @Override
    public List<Concursando> calcularNotaMultiplaConcursandos(Fase fase)
            throws Exception {
        return rnConcursando.calcularNotaMultiplaConcursandos(fase);
    }

    @Override
    public void inserirProva(Prova prova) throws Exception {
        rnProva.inserir(prova);
    }

    @Override
    public void atualizarProva(Prova prova) throws Exception {
        rnProva.atualizar(prova);
    }

    @Override
    public void removerProva(long id) throws Exception {
        rnProva.remover(id);
    }

    @Override
    public Prova consultarProvaPorId(long id) throws Exception {
        return rnProva.consultarPorId(id);
    }

    @Override
    public List<Prova> consultarTodosProva() throws Exception {
        // TODO Auto-generated method stub
        return rnProva.consultarTodos();
    }

    @Override
    public void inserirQuestaoDiscursiva(QuestaoDiscursiva questaoDiscursiva)
            throws Exception {
        // TODO Auto-generated method stub
        rnQuestaoDiscursiva.inserir(questaoDiscursiva);

    }

    @Override
    public void atualizarQuestaoDiscursiva(QuestaoDiscursiva questaoDiscursiva)
            throws Exception {
        // TODO Auto-generated method stub
        rnQuestaoDiscursiva.atualizar(questaoDiscursiva);

    }

    @Override
    public void removerQuestaoDiscursiva(long id) throws Exception {
        // TODO Auto-generated method stub
        rnQuestaoDiscursiva.remover(id);

    }

    @Override
    public QuestaoDiscursiva consultarQuestaoDiscursivaPorId(long id)
            throws Exception {
        // TODO Auto-generated method stub
        return rnQuestaoDiscursiva.consultarPorId(id);
    }

    @Override
    public List<QuestaoDiscursiva> consultarTodosQuestaoDiscursiva()
            throws Exception {
        // TODO Auto-generated method stub
        return rnQuestaoDiscursiva.consultarTodos();
    }

    @Override
    public List<QuestaoDiscursiva> consultarTodosPorGeneroQuestaoDiscursiva(
            Genero genero) throws Exception {
        return rnQuestaoDiscursiva.consultarTodosPorGenero(genero);
    }

    @Override
    public void inserirQuestaoMultiplaEscolha(
            QuestaoMultiplaEscolha questaoMultiplaEscolha) throws Exception {
        // TODO Auto-generated method stub
        rnQuestaoMultiplaEscolha.inserir(questaoMultiplaEscolha);

    }

    @Override
    public void atualizarQuestaoMultiplaEscolha(
            QuestaoMultiplaEscolha questaoMultiplaEscolha) throws Exception {
        // TODO Auto-generated method stub
        rnQuestaoMultiplaEscolha.atualizar(questaoMultiplaEscolha);

    }

    @Override
    public void removerQuestaoMultiplaEscolha(long id) throws Exception {
        // TODO Auto-generated method stub
        rnQuestaoMultiplaEscolha.remover(id);

    }

    @Override
    public QuestaoMultiplaEscolha consultarQuestaoMultiplaEscolhaPorId(long id)
            throws Exception {
        // TODO Auto-generated method stub
        return rnQuestaoMultiplaEscolha.consultarPorId(id);
    }

    @Override
    public List<QuestaoMultiplaEscolha> consultarTodosQuestaoMultiplaEscolha()
            throws Exception {
        // TODO Auto-generated method stub
        return rnQuestaoMultiplaEscolha.consultarTodos();
    }

    @Override
    public void inserirElaborador(Elaborador elaborador) throws Exception {
        rnElaborador.inserir(elaborador);
    }

    @Override
    public void atualizarElaborador(Elaborador elaborador) throws Exception {
        rnElaborador.atualizar(elaborador);
    }

    @Override
    public void removerElaborador(long id) throws Exception {
        rnElaborador.remover(id);
    }
   
    
    @Override
    public Elaborador consultarElaboradorPorId(long id) throws Exception {
        return rnElaborador.consultarPorId(id);
    }

    @Override
    public List<Elaborador> consultarTodosElaborador() throws Exception {
        return rnElaborador.consultarTodos();
    }

    @Override
    public Alternativa inserirAlternativaQuestao(Alternativa alternativa)
            throws Exception {
        return rnAlternativa.inserirAlternativa(alternativa);
    }

    @Override
    public void inserirAlternativa(Alternativa alternativa) throws Exception {
        rnAlternativa.inserir(alternativa);
    }

    @Override
    public void atualizarAlternativa(Alternativa alternativa) throws Exception {
        rnAlternativa.atualizar(alternativa);
    }

    @Override
    public void removerAlternativa(long id) throws Exception {
        rnAlternativa.remover(id);
    }

    @Override
    public Alternativa consultarAlternativaPorId(long id) throws Exception {
        return rnAlternativa.consultarPorId(id);
    }

    @Override
    public List<Alternativa> consultarTodosAlternativa() throws Exception {
        return rnAlternativa.consultarTodos();
    }

    @Override
    public void inserirDiaFase(DiaFase diaFase) throws Exception {
        rnDiaFase.inserir(diaFase);
    }

    @Override
    public void removerDiaFase(long id) throws Exception {
        rnDiaFase.remover(id);
    }

    @Override
    public void atualizarDiaFase(DiaFase diaFase) throws Exception {
        rnDiaFase.atualizar(diaFase);
    }

    @Override
    public DiaFase consultarDiaFasePorId(long id) throws Exception {
        return rnDiaFase.consultarPorId(id);
    }

    @Override
    public void inserirFase(Fase fase) throws Exception {
        rnFase.inserir(fase);
    }

    @Override
    public void removerFase(long id) throws Exception {
        rnFase.remover(id);
    }

    @Override
    public void atualizarFase(Fase fase) throws Exception {
        rnFase.atualizar(fase);
    }

    @Override
    public Fase consultarFasePorId(long id) throws Exception {
        return rnFase.consultarPorId(id);
    }

    @Override
    public List<QuestaoMultiplaEscolha> consultarTodosPorGeneroQuestaoMultiplaEscolha(
            Genero genero) throws Exception {
        return rnQuestaoMultiplaEscolha.consultarTodosPorGenero(genero);
    }

    @Override
    public void inserirGenero(Genero genero) throws Exception {
        rnGenero.inserir(genero);
    }

    @Override
    public void atualizarGenero(Genero genero) throws Exception {
        rnGenero.atualizar(genero);
    }

    @Override
    public void removerGenero(long id) throws Exception {
        rnGenero.remover(id);
    }

    @Override
    public Genero consultarGeneroPorId(long id) throws Exception {
        return rnGenero.consultarPorId(id);
    }

    @Override
    public List<Genero> consultarTodosGenero() throws Exception {
        return rnGenero.consultarTodos();
    }

    @Override
    public void inserirConcurso(Concurso concurso) throws Exception {
        rnConcurso.inserir(concurso);
    }

    @Override
    public void atualizarConcurso(Concurso concurso) throws Exception {
        rnConcurso.atualizar(concurso);
    }

    @Override
    public void removerConcurso(long id) throws Exception {
        rnConcurso.remover(id);
    }

    @Override
    public List<Concurso> consultarTodosConcurso() throws Exception {
        return rnConcurso.consultarTodos();
    }

    @Override
    public Concurso consultarConcursoPorId(long id) throws Exception {
        return rnConcurso.consultarPorId(id);
    }

    @Override
    public void inserirAreaConcurso(AreaConcurso areaconcurso) throws Exception {
        rnAreaConcurso.inserir(areaconcurso);
    }

    @Override
    public void atualizarAreaConcurso(AreaConcurso areaconcurso)
            throws Exception {
        rnAreaConcurso.atualizar(areaconcurso);
    }

    @Override
    public void removerAreaConcurso(long id) throws Exception {
        rnAreaConcurso.remover(id);
    }

    @Override
    public AreaConcurso consultarAreaConcursoPorId(long id) throws Exception {
        return rnAreaConcurso.consultarPorId(id);
    }

    @Override
    public List<AreaConcurso> consultarTodosAreaConcurso() throws Exception {
        return rnAreaConcurso.consultarTodos();
    }

    @Override
    public void inserirCartaoResposta(CartaoResposta cartaoResposta)
            throws Exception {
        rnCartaoResposta.inserir(cartaoResposta);
    }

    @Override
    public void atualizarCartaoResposta(CartaoResposta cartaoResposta)
            throws Exception {
        rnCartaoResposta.atualizar(cartaoResposta);
    }

    @Override
    public void removerCartaoResposta(long id) throws Exception {
        rnCartaoResposta.remover(id);
    }

    @Override
    public CartaoResposta consultarCartaoRespostaPorId(long id)
            throws Exception {
        return rnCartaoResposta.consultarPorId(id);
    }

    @Override
    public List<CartaoResposta> consultarTodosCartaoResposta() throws Exception {
        return rnCartaoResposta.consultarTodos();
    }

    @Override
    public void inserirLocal(Local local) throws Exception {
        rnLocal.inserir(local);
    }

    @Override
    public void atualizarLocal(Local local) throws Exception {
        rnLocal.atualizar(local);
    }

    @Override
    public void removerLocal(long id) throws Exception {
        rnLocal.remover(id);
    }

    @Override
    public Local consultarLocalPorId(long id) throws Exception {
        return rnLocal.consultarPorId(id);
    }

    @Override
    public List<Local> consultarTodosLocal() throws Exception {
        return rnLocal.consultarTodos();
    }

    @Override
    public void inserirGabarito(Gabarito gabarito) throws Exception {
        rnGabarito.inserir(gabarito);
    }

    @Override
    public void atualizarGabarito(Gabarito gabarito) throws Exception {
        rnGabarito.atualizar(gabarito);
    }

    @Override
    public void removerGabarito(long id) throws Exception {
        rnGabarito.remover(id);
    }

    @Override
    public Gabarito consultarGabaritoPorId(long id) throws Exception {
        return rnGabarito.consultarPorId(id);
    }

    @Override
    public List<Gabarito> consultarTodosGabarito() throws Exception {
        return rnGabarito.consultarTodos();
    }

    @Override
    public List<Gabarito> consultarGabaritoProva(long id_prova) throws Exception {
        return rnGabarito.consultarGabaritoProva(id_prova);
    }

    @Override
    public List<CartaoResposta> consultarCartoesRespostaConcursandoProva(Prova prova, Concursando concursando) throws Exception {
        return rnConcursando.consultarCartoesRespostaConcursandoProva(prova, concursando);
    }

   
}
