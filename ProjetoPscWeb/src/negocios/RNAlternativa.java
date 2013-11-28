package negocios;

import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import classes.Alternativa;
import daoImpl.AlternativaDAOImpl;
import factory.FactoryDAO;

public class RNAlternativa {

	AlternativaDAOImpl dao;

	public void inserir(Alternativa alternativa) throws Exception {
		if (alternativa.getTexto().equals("") || alternativa.getTexto() == null) {
			throw new Exception("Valor invalido");
		}
		dao.inserir(alternativa);
	}

	public void atualizar(Alternativa alternativa) throws Exception {
		if (alternativa.getTexto().equals("") || alternativa.getTexto() == null) {
			throw new Exception("Valor invalido");
		}
		dao.atualizar(alternativa);
	}

	public void remover(long id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id invalido");
		}
		dao.remover(id);
	}

	public Alternativa consultarPorId(long id) throws Exception {
		return dao.consultarPorId(id);
	}

	public List<Alternativa> consultarTodos() throws Exception {
		return dao.consultarTodos();
	}

        public Alternativa inserirAlternativa(Alternativa alternativa) throws Exception {
		if (alternativa.getTexto().equals("") || alternativa.getTexto() == null) {
			throw new Exception("Valor invalido");
		}
		return dao.inserirAlternativa(alternativa);
	}
        
	public RNAlternativa() {
		// TODO Auto-generated constructor stub
		dao = FactoryDAO.getAlternativaDAOImpl();
	}

}
