package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Boisson;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.repository.recette.BoissonRepository;

public class BoissonServiceImpl implements BoissonService {

    @Autowired
    BoissonRepository boissonRepository;

    @Override
    public void delete(Boisson t) {
        boissonRepository.delete(t);

    }

    @Override
    public void deleteById(Integer id) {
        boissonRepository.deleteById(id);

    }

    @Override
    public List<Boisson> findAll() {
        return boissonRepository.findAll();
    }

    @Override
    public Optional<Boisson> findById(Integer id) {
        return boissonRepository.findById(id);
    }

    @Override
    public Boolean insert(Boisson t) {
        String nomRecette = t.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            return false;
        }
        boissonRepository.save(t);
        return true;
    }

    @Override
    public Boisson update(Boisson t) {
        Optional<Boisson> opt = boissonRepository.findById(t.getId());
        if (opt.isPresent()) {
            Boisson recetteEnBase = opt.get();
            if (t.getNom() != null) {
                recetteEnBase.setNom(t.getNom());
            }
            recetteEnBase.setAuteur(t.getAuteur());
            recetteEnBase.setCout(t.getCout());
            recetteEnBase.setDifficulte(t.getDifficulte());
            recetteEnBase.setNbPersonne(t.getNbPersonne());
            recetteEnBase.setPhoto(t.getPhoto());
            recetteEnBase.setTags(t.getTags());
            recetteEnBase.setTemps(t.getTemps());
            recetteEnBase.setCommentaires(t.getCommentaires());
            recetteEnBase.setEtapes(t.getEtapes());
            return boissonRepository.save(recetteEnBase);
        } else {
            return null;
        }
    }

    @Override
    public List<Boisson> findAllWithTags() {
        return boissonRepository.findAllWithTags();
    }

    @Override
    public List<Boisson> findByAuteur(Utilisateur auteur) {
        return boissonRepository.findByAuteur(auteur);
    }

    @Override
    public List<Boisson> findByCout(Couts cout) {
        return boissonRepository.findByCout(cout);
    }

    @Override
    public List<Boisson> findByCoutNot(Couts cout) {
        return boissonRepository.findByCoutNot(cout);
    }

    @Override
    public List<Boisson> findByDifficulte(Difficulte difficulte) {
        return boissonRepository.findByDifficulte(difficulte);
    }

    @Override
    public List<Boisson> findByDifficulteNot(Difficulte difficulte) {
        return boissonRepository.findByDifficulteNot(difficulte);
    }

    @Override
    public List<Boisson> findByIngredientContaining(Ingredient ingredient) {
        return boissonRepository.findByIngredients(ingredient);
    }

    @Override
    public List<Boisson> findByIngredientNotContaining(Ingredient ingredient) {
        return boissonRepository.findByIngredientsNot(ingredient);
    }

    @Override
    public List<Boisson> findByTags(Tag tag) {
        return boissonRepository.findByTags(tag);
    }

    @Override
    public List<Boisson> findByTagsNot(Tag tag) {
        return boissonRepository.findByTagsNot(tag);
    }

}