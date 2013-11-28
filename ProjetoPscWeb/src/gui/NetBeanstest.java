/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.Concursando;
import classes.Endereco;
import daoImpl.ConcursandoDAOImpl;
import factory.FactoryDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAFAELSANTIAGO
 */
public class NetBeanstest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Concursando concursando = new Concursando();
            concursando.getAreaconcurso().setId(1);
            concursando.setNome("Teste");
            concursando.setCelular("1234-1234");
            concursando.setCpf("123.123.123-12");
            concursando.setRg("123.123-2");
            concursando.setTelefone("1234-4567");
            Calendar c = Calendar.getInstance();
            DateFormat f = DateFormat.getDateInstance();
            Date d = f.parse("12/12/2012");
            c.setTime(d);
            concursando.setData_nasc(c);
            Endereco endereco = new Endereco();
            endereco.setBairro("TESTE");
            endereco.setCep("12345-678");
            endereco.setCidade("Cidade");
            endereco.setComplemento("");
            endereco.setLogradouro("Teste");
            endereco.setNumero("123");
            endereco.setUf("PE");
            concursando.setEndereco(endereco);
            ConcursandoDAOImpl dao = FactoryDAO.getCocursandoDAOImpl();
            dao.inserir(concursando);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
