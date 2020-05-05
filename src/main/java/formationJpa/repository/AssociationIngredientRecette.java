package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.Ingredients.AssociationIngredientRecetteKey;

public interface AssociationIngredientRecette extends JpaRepository<AssociationIngredientRecette, AssociationIngredientRecetteKey> {

}
