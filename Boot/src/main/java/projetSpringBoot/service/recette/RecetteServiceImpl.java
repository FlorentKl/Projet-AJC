package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.repository.recette.RecetteRepository;

@Service
public class RecetteServiceImpl implements RecetteService {

    @Autowired
    RecetteRepository<Recette> recetteRepository;

    @Override
    public Recette insert(Recette recette) {
        String nomRecette = recette.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            return recette;
        }
        return recetteRepository.save(recette);
    }

    /// update Recette fait update, mais les controle se font dans les methodes
    /// specifique d'update de chaque classes
    @Override
    public Recette update(Recette recette) {
        Optional<Recette> opt = recetteRepository.findById(recette.getId());
        if (opt.isPresent()) {
            Recette recetteEnBase = opt.get();
            if (recette.getNom() != null) {
                recetteEnBase.setNom(recette.getNom());
            }
            recetteEnBase.setAuteur(recette.getAuteur());
            recetteEnBase.setCout(recette.getCout());
            recetteEnBase.setDifficulte(recette.getDifficulte());
            recetteEnBase.setNbPersonne(recette.getNbPersonne());
            recetteEnBase.setTags(recette.getTags());
            recetteEnBase.setTemps(recette.getTemps());
            recetteEnBase.setCommentaires(recette.getCommentaires());
            recetteEnBase.setEtapes(recette.getEtapes());
            return recetteRepository.save(recetteEnBase);
        } else {
            return null;
        }
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

    // findById
    @Override
    public Optional<Recette> findById(Integer id) {
        return recetteRepository.findById(id);
    }

    // findByNom
    @Override
    public Optional<Recette> findByNom(String nom) {
        return recetteRepository.findByNom(nom);
    }

    // findByNom
    @Override
    public List<Recette> findByNomContaining(String nom) {
        return recetteRepository.findByNomContainingIgnoreCase(nom);
    }

    // findByNom
    @Override
    public List<Recette> findByNomNotContaining(String nom) {
        return recetteRepository.findByNomNotContainingIgnoreCase(nom);
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
    public List<Recette> findAllWithTags() {
        return recetteRepository.findAllWithTags();
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
    public List<Recette> findByNote(Double note) {
        return recetteRepository.findByNote(note);
    }

}
