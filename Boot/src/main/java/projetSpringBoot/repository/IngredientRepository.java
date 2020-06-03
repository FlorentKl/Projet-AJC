package projetSpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.model.Ingredients.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Optional<Ingredient> findById(Integer id);

    Optional<Ingredient> findByNom(String nom);

    List<Ingredient> findByNomLike(String nom);

    List<Ingredient> findByNomNotLike(String nom);
}
