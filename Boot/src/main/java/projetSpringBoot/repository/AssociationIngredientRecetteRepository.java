package projetSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.model.Ingredients.AssociationIngredientRecette;
import projetSpringBoot.model.Ingredients.AssociationIngredientRecetteKey;

public interface AssociationIngredientRecetteRepository extends JpaRepository<AssociationIngredientRecette, AssociationIngredientRecetteKey> {
    Optional<AssociationIngredientRecette> findById(AssociationIngredientRecetteKey id);
}