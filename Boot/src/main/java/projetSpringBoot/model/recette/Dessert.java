package projetSpringBoot.model.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class Dessert extends Recette {

    public Dessert() {
        super();
    }

}
