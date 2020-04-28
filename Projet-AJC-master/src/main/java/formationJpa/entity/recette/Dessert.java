package formationJpa.entity.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class Dessert extends Recette{

	public Dessert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dessert(String nom, Integer id, Integer nbPersonne, Integer temps) {
		super(nom, id, nbPersonne, temps);
		// TODO Auto-generated constructor stub
	}

}
