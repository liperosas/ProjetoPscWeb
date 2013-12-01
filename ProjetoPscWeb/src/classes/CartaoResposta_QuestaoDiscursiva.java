/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Antônio
 */
@Entity
public class CartaoResposta_QuestaoDiscursiva {
    
    @EmbeddedId
    private CartaoResposta_QuestaoDiscursivaPK chaveComposta;
    
    private String resposta;

    public CartaoResposta_QuestaoDiscursivaPK getChaveComposta() {
        return chaveComposta;
    }

    public void setChaveComposta(CartaoResposta_QuestaoDiscursivaPK chaveComposta) {
        this.chaveComposta = chaveComposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    public CartaoResposta_QuestaoDiscursiva(){
        this.chaveComposta = new CartaoResposta_QuestaoDiscursivaPK();
    }
}
