package projetSpringBoot.entity.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Plat extends Recette{

	public Plat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plat(String nom, Integer id, Integer nbPersonne, Integer temps) {
		super(nom, id, nbPersonne, temps);
		// TODO Auto-generated constructor stub
	}

}
