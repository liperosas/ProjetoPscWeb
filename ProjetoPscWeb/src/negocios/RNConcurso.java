/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import classes.AreaConcurso;
import classes.Concurso;
import classes.DiaFase;
import classes.Fase;
import daoImpl.ConcursoDAOImpl;
import factory.FactoryDAO;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 
 * @author Antônio
 */
public class RNConcurso {

	ConcursoDAOImpl daoImpl;

	public RNConcurso() {
		daoImpl = FactoryDAO.getConcursoDAOImpl();
	}

	public void inserir(Concurso concurso) throws Exception {
		if (concurso == null) {
			throw new Exception("Concurso inválido");
		} else if (concurso.getNomeConcurso().equals("")
				|| concurso.getNomeConcurso() == null) {
			throw new Exception("Nome do concurso inválido");
		} else if (concurso.getAreasConcurso().size() <= 0) {
			throw new Exception(
					"É necessário que exista pelo menos uma área de concurso cadastrada");
		}
		for (AreaConcurso areaC : concurso.getAreasConcurso()) {
			for (Fase fase : areaC.getFases()) {
				for (DiaFase diaFase : fase.getDiasFase()) {
					for (int i = 0; i < fase.getDiasFase().size(); i++) {
						if (!diaFase.equals(fase.getDiasFase().get(i))){
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							if (diaFase.getDataDia().getTime().equals(fase.getDiasFase().get(i).getDataDia())){
								if (diaFase.getHoraFinal().HOUR < fase.getDiasFase().get(i).getHoraInicial().HOUR){
									throw new Exception("A hora final da fase anterior atravessa a hora inicial da proxima fase.");
								}
							}
						}
					}
				}
			}
		}
		for (AreaConcurso areaC : concurso.getAreasConcurso()) {
			int x = 0;
			for (AreaConcurso areaC2 : concurso.getAreasConcurso()) {

				if (areaC.getNome().equals(areaC2.getNome())) {
					x++;
					if (x > 1) {
						throw new Exception(
								"Duas Areas possuem o mesmo nome, remova ou altere uma delas para prosseguir.");
					}
				}
			}

		}
		daoImpl.inserir(concurso);
	}

	public void atualizar(Concurso concurso) throws Exception {
		if (concurso == null) {
			throw new Exception("Concurso inválido");
		} else if (concurso.getNomeConcurso().equals("")
				|| concurso.getNomeConcurso() == null) {
			throw new Exception("Nome do concurso inválido");
		} else if (concurso.getAreasConcurso().size() <= 0) {
			throw new Exception(
					"É necessário que exista pelo menos uma área de concurso cadastrada");
		}
		daoImpl.atualizar(concurso);
	}

	public void remover(long id) throws Exception {
		daoImpl.remover(id);
	}

	public Concurso consultarPorId(long id) throws Exception {
		return daoImpl.consultarPorId(id);
	}

	public List<Concurso> consultarTodos() throws Exception {
		return daoImpl.consultarTodos();
	}
}
