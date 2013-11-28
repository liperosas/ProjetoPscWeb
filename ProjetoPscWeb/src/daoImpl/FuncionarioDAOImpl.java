package daoImpl;

import java.util.List;

import javax.persistence.Query;

import classes.Funcionario;
import dao.IFuncionarioDAO;

public class FuncionarioDAOImpl extends GenericDAOImpl<Funcionario> implements
        IFuncionarioDAO {

    public FuncionarioDAOImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void inserir(Funcionario entidade) throws Exception {
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
    public void atualizar(Funcionario entidade) throws Exception {
        // TODO Auto-generated method stub
        // levantar exce��o na EntityNotFoundException Regra de negocios caso
        // n�o exista a entidade
        try {
            this.getManager().getTransaction().begin();
            this.getManager().merge(entidade);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            //this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void remover(long id) throws Exception {
        // TODO Auto-generated method stub
        Funcionario f = this.getManager().getReference(Funcionario.class,
                new Long(id));
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(f);
            this.getManager().getTransaction().commit();
        } catch (Exception ex) {
            this.getManager().getTransaction().rollback();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<Funcionario> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        Query query = this.getManager().createQuery(
                "SELECT f FROM Funcionario f");
        List<Funcionario> funcionarios = (List<Funcionario>) query
                .getResultList();
        return funcionarios;
    }

    @Override
    public Funcionario consultarPorId(long id) throws Exception {
        // TODO Auto-generated method stub
        Funcionario f = this.getManager().find(Funcionario.class, new Long(id));
        return f;
    }
}
