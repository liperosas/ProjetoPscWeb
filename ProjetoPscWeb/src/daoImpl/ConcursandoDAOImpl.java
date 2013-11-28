package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import classes.Alternativa;
import classes.AreaConcurso;
import classes.CartaoResposta;
import classes.Concursando;
import classes.DiaFase;
import classes.Fase;
import classes.Gabarito;
import classes.Prova;
import classes.Questao;
import classes.QuestaoMultiplaEscolha;
import classes.RespostasProva;
import dao.IConcursandoDAO;

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
	public List<Concursando> classificarConcursandos(Fase fase)
			throws Exception {
		List<Concursando> concursandos = new ArrayList<Concursando>();
		for (DiaFase diaFase : fase.getDiasFase()) {
			for (Prova prova : diaFase.getProvas()) {
				Query query = this
						.getManager()
						.createQuery(
								"SELECT g FROM Gabarito g where g.id_prova = :id_prova");
				query.setParameter(":id_prova", prova.getId());
				Gabarito gabarito = (Gabarito) query.getResultList().get(0);
				for (RespostasProva cartaoResposta : prova.getCartoesResposta()) {
					if (cartaoResposta instanceof CartaoResposta) {
						for (Alternativa alternativa : cartaoResposta
								.getAlternativas()) {
							for (Alternativa alternativaC : gabarito
									.getAlternativas()) {
								if (alternativa.getId() == alternativaC.getId()) {
									((CartaoResposta) cartaoResposta)
											.setCorretaMultiplaEscolha(((CartaoResposta) cartaoResposta)
													.getCorretaMultiplaEscolha() + 1);
								}
							}
						}
					}
				}
			}
		}
		return concursandos;
	}
}
