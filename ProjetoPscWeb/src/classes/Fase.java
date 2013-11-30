package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Fase {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_areaconcurso")
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private AreaConcurso areaconcurso;

    @ManyToOne(fetch = FetchType.EAGER)
    private Local local;
    
    @OneToMany(mappedBy = "fase")
    @Cascade(CascadeType.ALL)
    private List<DiaFase> diasFase;

    private boolean classificacaoRealizada;
    
    public Fase(int id, AreaConcurso areaconcurso, Calendar datainicial,
            Calendar datafinal) {
        super();
        this.id = id;
        this.areaconcurso = areaconcurso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AreaConcurso getAreaconcurso() {
        return areaconcurso;
    }

    public void setAreaconcurso(AreaConcurso areaconcurso) {
        this.areaconcurso = areaconcurso;
    }

    public Fase() {
       // TODO Auto-generated constructor stub
        this.diasFase = new ArrayList<DiaFase>();
        this.areaconcurso = new AreaConcurso();
        this.local = new Local();
    }

    public List<DiaFase> getDiasFase() {
        return diasFase;
    }

    public void setDiasFase(List<DiaFase> diasFase) {
        this.diasFase = diasFase;
    }

    /**
     * @return the classificacaoRealizada
     */
    public boolean isClassificacaoRealizada() {
        return classificacaoRealizada;
    }

    /**
     * @param classificacaoRealizada the classificacaoRealizada to set
     */
    public void setClassificacaoRealizada(boolean classificacaoRealizada) {
        this.classificacaoRealizada = classificacaoRealizada;
    }

    /**
     * @return the local
     */
    public Local getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(Local local) {
        this.local = local;
    }

}
