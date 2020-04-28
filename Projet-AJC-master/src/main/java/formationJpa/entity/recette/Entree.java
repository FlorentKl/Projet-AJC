package formationJpa.entity.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class Entree extends Recette{

	public Entree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entree(String nom, Integer id, Integer nbPersonne, Integer temps) {
		super(nom, id, nbPersonne, temps);
		// TODO Auto-generated constructor stub
	}

}
