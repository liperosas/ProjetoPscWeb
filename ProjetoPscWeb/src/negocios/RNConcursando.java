package negocios;

import classes.CartaoResposta;
import java.util.List;

import classes.Concursando;
import classes.DiaFase;
import classes.Fase;
import classes.Gabarito;
import classes.Prova;
import classes.RespostasProva;
import daoImpl.ConcursandoDAOImpl;
import factory.FactoryDAO;

public class RNConcursando extends RNPessoa {

    ConcursandoDAOImpl dao;

    public void inserir(Concursando concursando) throws Exception {
        this.validarInserir(concursando);
        dao.inserir(concursando);
    }

    public void atualizar(Concursando concursando) throws Exception {
        this.validarAlterar(concursando);
        dao.atualizar(concursando);
    }

    public void remover(long id) throws Exception {
        this.validarRemover(id);
        dao.remover(id);
    }

    public Concursando consultarPorId(long id) throws Exception {
        return dao.consultarPorId(id);
    }

    public List<Concursando> consultarTodos() throws Exception {
        return dao.consultarTodos();
    }

    public Concursando logarConcursando(String login, String senha)
            throws Exception {
        Concursando concursando = dao.logarConcursando(login, senha);
        if (concursando == null) {
            throw new Exception("Login ou Senha Inv�lidos");
        }
        return concursando;
    }

    public List<Concursando> calcularNotaMultiplaConcursandos(Fase fase) throws Exception {
        for (DiaFase dfase : fase.getDiasFase()) {
            for (Prova prova : dfase.getProvas()) {
                boolean verifica = false;
                for (RespostasProva resProva : prova.getCartoesResposta()) {
                    if (resProva instanceof Gabarito) {
                        verifica = true;
                    }
                }
                if (!verifica) {
                    throw new Exception("Não existe gabarito para esta prova");
                }
            }
        }
        return dao.calcularNotaMultiplaConcursandos(fase);
    }

    public List<CartaoResposta> consultarCartoesRespostaConcursandoProva(Prova prova, Concursando concursando) throws Exception {
        return dao.consultarCartoesRespostaConcursandoProva(prova, concursando);
    }

    public RNConcursando() {
        // TODO Auto-generated constructor stub
        dao = FactoryDAO.getCocursandoDAOImpl();
    }
}
