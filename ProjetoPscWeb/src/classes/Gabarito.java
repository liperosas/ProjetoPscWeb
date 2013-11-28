package classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GABARITO")
public class Gabarito extends RespostasProva {

	public Gabarito() {
		// TODO Auto-generated constructor stub
	}

}
