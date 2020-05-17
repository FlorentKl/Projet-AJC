package projetSpringBoot.service;

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

public interface RecetteService {
    Recette insert(Recette recette);

    Entree update(Entree entree);

    Plat update(Plat plat);

    Dessert update(Dessert dessert);

    Boisson update(Boisson boisson);

    // delete
    void delete(Recette recette);

    // deleteByKey
    void deleteById(Integer id);

    // Find All
    List<Recette> findAll();

    List<Entree> findAllEntree();

    List<Plat> findAllPlat();

    List<Dessert> findAllDessert();

    List<Boisson> findAllBoisson();

    // findById
    Optional<Recette> findById(Integer id);

    // findByNom
    List<Recette> findRecetteByNom(String nom);

    List<Entree> findEntreeByNom(String nom);

    List<Plat> findPlatByNom(String nom);

    List<Dessert> findDessertByNom(String nom);

    List<Boisson> findBoissonByNom(String nom);

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