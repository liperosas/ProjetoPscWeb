package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@PrimaryKeyJoinColumn(name = "id_questao")
public class QuestaoMultiplaEscolha extends Questao {

    @OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Alternativa> alternativas;

    public QuestaoMultiplaEscolha() {
        // TODO Auto-generated constructor stub
        alternativas = new ArrayList<Alternativa>();
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
}
