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
public class RecetteService {

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
    public void insert(Recette recette) {
        String nomRecette = recette.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            throw new IllegalArgumentException("Nom null pour Recette");
        }
        recetteRepository.save(recette);
    }

    // TODO finir update

    /// update Recette fait update, mais les controle se font dans les methodes
    /// specifique d'update de chaque classes
    private Recette updateRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    public Entree update(Entree entree) {
        return (Entree) updateRecette(entree);
    }

    public Plat update(Plat plat) {
        return (Plat) updateRecette(plat);
    }

    public Dessert update(Dessert dessert) {
        return (Dessert) updateRecette(dessert);
    }

    public Boisson update(Boisson boisson) {
        return (Boisson) updateRecette(boisson);
    }

    //delete
    public void delete(Recette recette) {
        recetteRepository.delete(recette);
    }

    //deleteByKey
    public void deleteById(Integer id) {
        recetteRepository.deleteById(id);
    }

    // Find All
    public List<Recette> findAll() {
        return recetteRepository.findAll();
    }
    
    public List<Entree> findAllEntree(){
        return entreeRepository.findAll();
    }
    
    public List<Plat> findAllPlat(){
        return platRepository.findAll();
    }
    
    public List<Dessert> findAllDessert(){
        return dessertRepository.findAll();
    }
    
    public List<Boisson> findAllBoisson(){
        return boissonRepository.findAll();
    }

    // findById
    public Optional<Recette> findById(Integer id) {
        return recetteRepository.findById(id);
    }

    //findByNom
    public List<Recette> findRecetteByNom(String nom){
        return recetteRepository.findByNomLike(nom);
    }
    
    public List<Entree> findEntreeByNom(String nom) {
        return entreeRepository.findByNom(nom);
    }

    public List<Plat> findPlatByNom(String nom) {
        return platRepository.findByNom(nom);
    }

    public List<Dessert> findDessertByNom(String nom) {
        return dessertRepository.findByNom(nom);
    }

    public List<Boisson> findBoissonByNom(String nom) {
        return boissonRepository.findByNom(nom);
    }

    //findByIngredientContaining
    public List<Recette> findByIngredientContaining(Ingredient ingredient) {
        return recetteRepository.findByIngredients(ingredient);
    }

    //findByIngredientNotContaining
    public List<Recette> findByIngredientNotContaining(Ingredient ingredient) {
        return recetteRepository.findByIngredientsNot(ingredient);
    }
    
    // TODO - findByTag
    public List<Recette> findByTags(Tag tag) {
        return recetteRepository.findByTags(tag);
    }

    public List<Recette> findByTagsNot(Tag tag) {
        return recetteRepository.findByTagsNot(tag);
    }

    public List<Recette> findByAuteur(Utilisateur auteur) {
        return recetteRepository.findByAuteur(auteur);
    }

    public List<Recette> findByDifficulte(Difficulte difficulte) {
        return recetteRepository.findByDifficulte(difficulte);
    }

    public List<Recette> findByDifficulteNot(Difficulte difficulte) {
        return recetteRepository.findByDifficulteNot(difficulte);
    }

    public List<Recette> findByCout(Couts cout) {
        return recetteRepository.findByCout(cout);
    }

    public List<Recette> findByCoutNot(Couts cout) {
        return recetteRepository.findByCoutNot(cout);
    }
    
    public  List<Recette> findAllWithTags(){
    	return recetteRepository.findAllWithTags();
    }
    
    // TODO - findByNote
}
