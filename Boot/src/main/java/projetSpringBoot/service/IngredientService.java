package projetSpringBoot.service;

import java.util.Optional;

import projetSpringBoot.model.Ingredients.Ingredient;

public interface IngredientService extends GenericInterfaceService<Ingredient, Integer> {
    Optional<Ingredient> findByNom(String nom);
}