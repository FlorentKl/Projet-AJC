package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.Ingredients.AssociationIngredientRecette;
import formationJpa.entity.Ingredients.AssociationIngredientRecetteKey;

public interface AssociationIngredientRecetteRepository
        extends JpaRepository<AssociationIngredientRecette, AssociationIngredientRecetteKey> {

}
