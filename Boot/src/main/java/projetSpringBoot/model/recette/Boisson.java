package projetSpringBoot.model.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Boisson extends Recette {

    public Boisson() {
        super();
    }

}
