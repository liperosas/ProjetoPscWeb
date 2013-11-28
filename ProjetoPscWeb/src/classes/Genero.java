package classes;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
public class Genero {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 30, nullable = false)
    private String genero;
    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Questao> questoes;
    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Prova> provas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Genero(long id, String genero) {
        super();
        this.id = id;
        this.genero = genero;
    }

    public Genero() {
        // TODO Auto-generated constructor stub
        provas = new ArrayList<Prova>();
        questoes = new ArrayList<Questao>();
    }

    /**
     * @return the provas
     */
    public List<Prova> getProvas() {
        return provas;
    }

    /**
     * @param provas the provas to set
     */
    public void setProvas(List<Prova> provas) {
        this.provas = provas;
    }

    /**
     * @return the questoes
     */
    public List<Questao> getQuestoes() {
        return questoes;
    }

    /**
     * @param questoes the questoes to set
     */
    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
}
