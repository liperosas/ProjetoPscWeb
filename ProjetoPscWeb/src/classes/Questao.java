package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "questao")
public class Questao {

    @Id
    @GeneratedValue
    @Column(name = "id_questao")
    private long id;
    
    @Type(type = "text")
    private String texto;
    
    @Column(length = 20, nullable = true)
    private String referencia;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "questao_prova",
            joinColumns =
            @JoinColumn(name = "id_questao"),
            inverseJoinColumns =
            @JoinColumn(name = "id_prova"))
    private List<Prova> provas;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_genero", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Genero genero;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_elaborador", updatable = true, insertable = true)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Elaborador elaborador;

    public Questao(long id, String texto, String referencia, Genero genero,
            Elaborador elaborador) {
        super();
        this.id = id;
        this.texto = texto;
        this.referencia = referencia;
        this.genero = genero;
        this.elaborador = elaborador;
        this.referencia = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Elaborador getElaborador() {
        return elaborador;
    }

    public void setElaborador(Elaborador elaborador) {
        this.elaborador = elaborador;
    }

    public Questao() {
        // TODO Auto-generated constructor stub
        provas = new ArrayList<Prova>();
        elaborador = new Elaborador();
        genero = new Genero();
    }

}
