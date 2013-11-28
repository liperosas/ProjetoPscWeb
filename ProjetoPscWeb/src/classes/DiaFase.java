/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "dataFase")
public class DiaFase {

    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private Calendar dataDia;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horaInicial;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horaFinal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fase")
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Fase fase;
    @OneToMany(mappedBy = "diaFase")
    @Cascade(CascadeType.ALL)
    private List<Prova> provas;

    public DiaFase() {
        dataDia = Calendar.getInstance();
        horaInicial = Calendar.getInstance();
        horaFinal = Calendar.getInstance();
        fase = new Fase();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDataDia() {
        return dataDia;
    }

    public void setDataDia(Calendar dataDia) {
        this.dataDia = dataDia;
    }

    public Calendar getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Calendar horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Calendar getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Calendar horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
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
}
