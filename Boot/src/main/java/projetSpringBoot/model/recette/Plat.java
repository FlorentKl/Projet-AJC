package projetSpringBoot.model.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Plat extends Recette {

    public Plat() {
        super();
    }

}
