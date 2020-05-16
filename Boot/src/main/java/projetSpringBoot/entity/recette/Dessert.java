package projetSpringBoot.entity.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class Dessert extends Recette {

    public Dessert() {
        super();
    }

}
