package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Boisson;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Entree;
import projetSpringBoot.model.recette.Plat;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.service.GenericInterfaceService;

public interface RecetteService extends GenericInterfaceService<Recette, Integer> {
    void insert(Recette recette);

    // Find All
    List<Recette> findAll();

    // findById
    Optional<Recette> findById(Integer id);

    // findByNom
    List<Recette> findRecetteByNom(String nom);

    // findByIngredientContaining
    List<Recette> findByIngredientContaining(Ingredient ingredient);

    // findByIngredientNotContaining
    List<Recette> findByIngredientNotContaining(Ingredient ingredient);

    // TODO - findByTag
    List<Recette> findByTags(Tag tag);

    List<Recette> findByTagsNot(Tag tag);

    List<Recette> findByAuteur(Utilisateur auteur);

    List<Recette> findByDifficulte(Difficulte difficulte);

    List<Recette> findByDifficulteNot(Difficulte difficulte);

    List<Recette> findByCout(Couts cout);

    List<Recette> findByCoutNot(Couts cout);

    List<Recette> findAllWithTags();

    // TODO - findByNote
}