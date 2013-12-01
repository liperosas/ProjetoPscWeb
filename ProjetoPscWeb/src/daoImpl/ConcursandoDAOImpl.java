package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import classes.Alternativa;
import classes.AreaConcurso;
import classes.CartaoResposta;
import classes.Concursando;
import classes.Concurso;
import classes.DiaFase;
import classes.Fase;
import classes.Gabarito;
import classes.Prova;
import classes.RespostasProva;
import dao.IConcursandoDAO;
import factory.FactoryDAO;

public class ConcursandoDAOImpl extends GenericDAOImpl<Concursando> implements
        IConcursandoDAO {

    public ConcursandoDAOImpl() {
    }

    @Override
    public void inserir(Concursando entidade) throws Exception {
        // TODO Auto-generated method stub
        try {
            this.getManager().getTransaction().begin();
            this.getManager().persist(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Concursando entidade) throws Exception {
        // TODO Auto-generated method stub
        try {
            this.getManager().getTransaction().begin();
            this.getManager().merge(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void remover(long id) throws Exception {
        // TODO Auto-generated method stub
        Concursando c = this.getManager().getReference(Concursando.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(c);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Concursando> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT c FROM Concursando c");
        List<Concursando> Concursandos = (List<Concursando>) query
                .getResultList();
        return Concursandos;
    }

    @Override
    public Concursando consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        Concursando c = this.getManager().find(Concursando.class, new Long(id));
        return c;
    }

    @Override
    public Concursando logarConcursando(String login, String senha) {
        Query query = this
                .getManager()
                .createQuery(
                "SELECT c FROM Concursando c where c.login = :login and c.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        Concursando concursando = (Concursando) query.getSingleResult();
        return concursando;
    }

    @Override
    public List<Concursando> calcularNotaMultiplaConcursandos(Fase fase) throws Exception {
        List<Concursando> concursandos = new ArrayList<Concursando>();
        for (DiaFase diaFase : fase.getDiasFase()) {
            for (Prova prova : diaFase.getProvas()) {
                Query query = this.getManager().createQuery("SELECT g FROM Gabarito g where g.prova = :prova");
                query.setParameter("prova", prova);
                Gabarito gabarito = (Gabarito) query.getResultList().get(0);
                for (RespostasProva cartaoResposta : prova.getCartoesResposta()) {
                    if (cartaoResposta instanceof CartaoResposta) {
                        int qntdMultipla = 0;
                        for (Alternativa alternativa : cartaoResposta.getAlternativas()) {
                            for (Alternativa alternativaC : gabarito.getAlternativas()) {
                                if (alternativa.getId() == alternativaC.getId()) {
                                    qntdMultipla++;
                                }
                            }
                        }
                        CartaoRespostaDAOImpl dao = FactoryDAO.getCartaoRespostaDAOImpl();
                        ((CartaoResposta) cartaoResposta).setCorretaMultiplaEscolha(qntdMultipla);
                        dao.atualizar((CartaoResposta) cartaoResposta);
                    }
                }
            }
        }
        Query query = this.getManager().createQuery("SELECT ac FROM AreaConcurso ac WHERE ac = :ac");
        query.setParameter("ac", fase.getAreaconcurso());
        List<AreaConcurso> areaConcurso = new ArrayList<AreaConcurso>();
        areaConcurso = query.getResultList();
        concursandos = areaConcurso.get(0).getConcursando();
        return concursandos;
    }

    public List<CartaoResposta> consultarCartoesRespostaConcursandoProva(Prova prova, Concursando concursando) throws Exception {
        List<CartaoResposta> cartaoResposta = new ArrayList<CartaoResposta>();
        Query query = this.getManager().createQuery("SELECT cr FROM CartaoResposta cr WHERE cr.concursando = :concursando AND cr.prova = :prova");
        query.setParameter("prova", prova);
        query.setParameter("concursando", concursando);
        cartaoResposta = query.getResultList();
        return cartaoResposta;
    }
    
}
