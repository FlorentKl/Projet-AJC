package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.Ingredients.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
