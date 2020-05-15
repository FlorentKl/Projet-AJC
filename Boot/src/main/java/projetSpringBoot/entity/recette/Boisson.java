package projetSpringBoot.entity.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Boisson extends Recette{

	public Boisson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boisson(String nom, Integer id, Integer nbPersonne, Integer temps) {
		super(nom, id, nbPersonne, temps);
		// TODO Auto-generated constructor stub
	}

}
