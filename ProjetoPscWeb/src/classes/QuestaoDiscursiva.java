package classes;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Type;


@Entity
@PrimaryKeyJoinColumn (name="id_questao")
public class QuestaoDiscursiva extends Questao{

    @Type(type = "text")
    private String resposta;
    
    private int linhas;
    
	public QuestaoDiscursiva() {
		// TODO Auto-generated constructor stub
	}

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

}
