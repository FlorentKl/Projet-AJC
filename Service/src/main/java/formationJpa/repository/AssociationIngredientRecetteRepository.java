package formationJpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.Ingredients.AssociationIngredientRecette;
import formationJpa.entity.Ingredients.AssociationIngredientRecetteKey;

public interface AssociationIngredientRecetteRepository extends JpaRepository<AssociationIngredientRecette, AssociationIngredientRecetteKey> {
    Optional<AssociationIngredientRecette> findById(AssociationIngredientRecetteKey id);
}