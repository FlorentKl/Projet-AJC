package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import projetSpringBoot.repository.recette.BoissonRepository;
import projetSpringBoot.repository.recette.DessertRepository;
import projetSpringBoot.repository.recette.EntreeRepository;
import projetSpringBoot.repository.recette.PlatRepository;
import projetSpringBoot.repository.recette.RecetteRepository;

@Service
public class RecetteServiceImpl implements RecetteService {

    @Autowired
    RecetteRepository<Recette> recetteRepository;

    @Autowired
    EntreeRepository entreeRepository;

    @Autowired
    PlatRepository platRepository;

    @Autowired
    DessertRepository dessertRepository;

    @Autowired
    BoissonRepository boissonRepository;

    // TODO Un insert pour tous, ou alors s�parer par classes pour controle avec une
    // methode insert g�n�rale ?
    @Override
    public Recette insert(Recette recette) {
        String nomRecette = recette.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            throw new IllegalArgumentException("Nom null pour Recette");
        }
        return recetteRepository.save(recette);
    }

    // TODO finir update

    @Override
    public Entree update(Entree entree) {
        return (Entree) updateRecette(entree);
    }

    @Override
    public Plat update(Plat plat) {
        return (Plat) updateRecette(plat);
    }

    @Override
    public Dessert update(Dessert dessert) {
        return (Dessert) updateRecette(dessert);
    }

    @Override
    public Boisson update(Boisson boisson) {
        return (Boisson) updateRecette(boisson);
    }

    // delete
    @Override
    public void delete(Recette recette) {
        recetteRepository.delete(recette);
    }

    // deleteByKey
    @Override
    public void deleteById(Integer id) {
        recetteRepository.deleteById(id);
    }

    // Find All
    @Override
    public List<Recette> findAll() {
        return recetteRepository.findAll();
    }

    @Override
    public List<Entree> findAllEntree() {
        return entreeRepository.findAll();
    }

    @Override
    public List<Plat> findAllPlat() {
        return platRepository.findAll();
    }

    @Override
    public List<Dessert> findAllDessert() {
        return dessertRepository.findAll();
    }

    @Override
    public List<Boisson> findAllBoisson() {
        return boissonRepository.findAll();
    }

    // findById
    @Override
    public Optional<Recette> findById(Integer id) {
        return recetteRepository.findById(id);
    }

    // findByNom
    @Override
    public List<Recette> findRecetteByNom(String nom) {
        return recetteRepository.findByNomLike(nom);
    }

    @Override
    public List<Entree> findEntreeByNom(String nom) {
        return entreeRepository.findByNom(nom);
    }

    @Override
    public List<Plat> findPlatByNom(String nom) {
        return platRepository.findByNom(nom);
    }

    @Override
    public List<Dessert> findDessertByNom(String nom) {
        return dessertRepository.findByNom(nom);
    }

    @Override
    public List<Boisson> findBoissonByNom(String nom) {
        return boissonRepository.findByNom(nom);
    }

    // findByIngredientContaining
    @Override
    public List<Recette> findByIngredientContaining(Ingredient ingredient) {
        return recetteRepository.findByIngredients(ingredient);
    }

    // findByIngredientNotContaining
    @Override
    public List<Recette> findByIngredientNotContaining(Ingredient ingredient) {
        return recetteRepository.findByIngredientsNot(ingredient);
    }

    @Override
    public List<Recette> findByTags(Tag tag) {
        return recetteRepository.findByTags(tag);
    }

    @Override
    public List<Recette> findByTagsNot(Tag tag) {
        return recetteRepository.findByTagsNot(tag);
    }

    @Override
    public List<Recette> findByAuteur(Utilisateur auteur) {
        return recetteRepository.findByAuteur(auteur);
    }

    @Override
    public List<Recette> findByDifficulte(Difficulte difficulte) {
        return recetteRepository.findByDifficulte(difficulte);
    }

    @Override
    public List<Recette> findByDifficulteNot(Difficulte difficulte) {
        return recetteRepository.findByDifficulteNot(difficulte);
    }

    @Override
    public List<Recette> findByCout(Couts cout) {
        return recetteRepository.findByCout(cout);
    }

    @Override
    public List<Recette> findByCoutNot(Couts cout) {
        return recetteRepository.findByCoutNot(cout);
    }

    @Override
    public List<Recette> findAllWithTags() {
        return recetteRepository.findAllWithTags();
    }

    /// update Recette fait update, mais les controle se font dans les methodes
    /// specifique d'update de chaque classes
    private Recette updateRecette(Recette recette) {
        return recetteRepository.save(recette);
    }
}
