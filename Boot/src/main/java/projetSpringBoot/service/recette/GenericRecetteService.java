package projetSpringBoot.service.recette;

import java.util.List;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.tag.Tag;

public interface GenericRecetteService<T> {
    // findByNom
    List<T> findByNomContaining(String nom);

    List<T> findByNomNotContaining(String nom);

    // findByIngredientContaining
    List<T> findByIngredientContaining(Ingredient ingredient);

    List<T> findByIngredientNotContaining(Ingredient ingredient);

    // FindByTag
    List<T> findAllWithTags();

    List<T> findByTags(Tag tag);

    List<T> findByTagsNot(Tag tag);

    // findByAuteur
    List<T> findByAuteur(Utilisateur auteur);

    // findByDifficulte
    List<T> findByDifficulte(Difficulte difficulte);

    List<T> findByDifficulteNot(Difficulte difficulte);

    // findByCout
    List<T> findByCout(Couts cout);

    List<T> findByCoutNot(Couts cout);

    // TODO - findByNote
}