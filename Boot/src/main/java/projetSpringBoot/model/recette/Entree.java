package projetSpringBoot.model.recette;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class Entree extends Recette {

    public Entree() {
        super();
    }

}
