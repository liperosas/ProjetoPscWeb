/**
 *
 */
package factory;

import daoImpl.AlternativaDAOImpl;
import daoImpl.AreaConcursoDAOImpl;
import daoImpl.CartaoRespostaDAOImpl;
import daoImpl.ConcursandoDAOImpl;
import daoImpl.ConcursoDAOImpl;
import daoImpl.DiaFaseDAOImpl;
import daoImpl.ElaboradorDAOImpl;
import daoImpl.EmpresaDAOImpl;
import daoImpl.FaseDAOImpl;
import daoImpl.FuncionarioDAOImpl;
import daoImpl.GabaritoDAOImpl;
import daoImpl.GeneroDAOImpl;

import daoImpl.LocalDAOImpl;
import daoImpl.ProvaDAOImpl;
import daoImpl.QuestaoDiscursivaDAOImpl;
import daoImpl.QuestaoMultiplaDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryDAO {

    /**
     *
     */
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    static {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        factory = Persistence.createEntityManagerFactory("Projeto_PSC");
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = factory.createEntityManager();
        }
    }

    public static FuncionarioDAOImpl getFuncionarioDAOImpl() {
        FuncionarioDAOImpl funcionarioDAOImpl = new FuncionarioDAOImpl();
        funcionarioDAOImpl.setManager(entityManager);
        return funcionarioDAOImpl;
    }

    public static ElaboradorDAOImpl getElaboradorDAOImpl() {
        ElaboradorDAOImpl elaboradorDAOImpl = new ElaboradorDAOImpl();
        elaboradorDAOImpl.setManager(entityManager);
        return elaboradorDAOImpl;
    }

    public static ConcursandoDAOImpl getCocursandoDAOImpl() {
        ConcursandoDAOImpl concursandoDAOImpl = new ConcursandoDAOImpl();
        concursandoDAOImpl.setManager(entityManager);
        return concursandoDAOImpl;
    }

    public static AlternativaDAOImpl getAlternativaDAOImpl() {
        AlternativaDAOImpl alternativaDAOImpl = new AlternativaDAOImpl();
        alternativaDAOImpl.setManager(entityManager);
        return alternativaDAOImpl;
    }

    public static AreaConcursoDAOImpl getAreaConcursoDAOImpl() {
        AreaConcursoDAOImpl areaConcursoDAOImpl = new AreaConcursoDAOImpl();
        areaConcursoDAOImpl.setManager(entityManager);
        return areaConcursoDAOImpl;
    }

    public static CartaoRespostaDAOImpl getCartaoRespostaDAOImpl() {
        CartaoRespostaDAOImpl cartaoRespostaDAOImpl = new CartaoRespostaDAOImpl();
        cartaoRespostaDAOImpl.setManager(entityManager);
        return cartaoRespostaDAOImpl;
    }

    public static EmpresaDAOImpl getEmpresaDAOImpl() {
        EmpresaDAOImpl empresaDAOImpl = new EmpresaDAOImpl();
        empresaDAOImpl.setManager(entityManager);
        return empresaDAOImpl;
    }

    public static FaseDAOImpl getFaseDAOImpl() {
        FaseDAOImpl faseDAOImpl = new FaseDAOImpl();
        faseDAOImpl.setManager(entityManager);
        return faseDAOImpl;
    }

    public static GabaritoDAOImpl getGabaritoDAOImpl() {
        GabaritoDAOImpl gabaritoDAOImpl = new GabaritoDAOImpl();
        gabaritoDAOImpl.setManager(entityManager);
        return gabaritoDAOImpl;
    }

    public static GeneroDAOImpl getGeneroDAOImpl() {
        GeneroDAOImpl generoDAOImpl = new GeneroDAOImpl();
        generoDAOImpl.setManager(entityManager);
        return generoDAOImpl;
    }

    public static LocalDAOImpl getLocalDAOImpl() {
        LocalDAOImpl localDAOImpl = new LocalDAOImpl();
        localDAOImpl.setManager(entityManager);
        return localDAOImpl;
    }

    public static ProvaDAOImpl getProvaDAOImpl() {
        ProvaDAOImpl provaDAOImpl = new ProvaDAOImpl();
        provaDAOImpl.setManager(entityManager);
        return provaDAOImpl;
    }

    public static QuestaoDiscursivaDAOImpl getQuestaoDiscussivaDAOImpl() {
        QuestaoDiscursivaDAOImpl questaoDiscussivaDAOImpl = new QuestaoDiscursivaDAOImpl();
        questaoDiscussivaDAOImpl.setManager(entityManager);
        return questaoDiscussivaDAOImpl;
    }

    public static QuestaoMultiplaDAOImpl getQuestaoMultiplaDAOImpl() {
        QuestaoMultiplaDAOImpl questaoMultiplaDAOImpl = new QuestaoMultiplaDAOImpl();
        questaoMultiplaDAOImpl.setManager(entityManager);
        return questaoMultiplaDAOImpl;
    }

    public static DiaFaseDAOImpl getDiaFaseDAOImpl() {
        DiaFaseDAOImpl diaFaseDAOImpl = new DiaFaseDAOImpl();
        diaFaseDAOImpl.setManager(entityManager);
        return diaFaseDAOImpl;
    }

    public static ConcursoDAOImpl getConcursoDAOImpl() {
        ConcursoDAOImpl concursoDAOImpl = new ConcursoDAOImpl();
        concursoDAOImpl.setManager(entityManager);
        return concursoDAOImpl;
    }
}
