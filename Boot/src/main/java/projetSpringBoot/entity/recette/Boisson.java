package projetSpringBoot.entity.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Boisson extends Recette {

    public Boisson() {
        super();
    }

}
