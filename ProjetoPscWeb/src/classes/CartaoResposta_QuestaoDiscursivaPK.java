/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Antônio
 */
@Embeddable
public class CartaoResposta_QuestaoDiscursivaPK implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cartaoresposta")
    private CartaoResposta cartaoResposta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_questaodiscursiva")
    private QuestaoDiscursiva questDiscursiva;

    public CartaoResposta getCartaoResposta() {
        return cartaoResposta;
    }

    public void setCartaoResposta(CartaoResposta cartaoResposta) {
        this.cartaoResposta = cartaoResposta;
    }

    public QuestaoDiscursiva getQuestDiscursiva() {
        return questDiscursiva;
    }

    public void setQuestDiscursiva(QuestaoDiscursiva questDiscursiva) {
        this.questDiscursiva = questDiscursiva;
    }

    public CartaoResposta_QuestaoDiscursivaPK() {
        this.cartaoResposta = new CartaoResposta();
        this.questDiscursiva = new QuestaoDiscursiva();
    }
}
