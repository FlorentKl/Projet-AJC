package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.repository.recette.DessertRepository;

@Service
public class DessertServiceImpl implements DessertService {

    @Autowired
    DessertRepository dessertRepository;

    @Override
    public void delete(Dessert t) {
        dessertRepository.delete(t);

    }

    @Override
    public void deleteById(Integer id) {
        dessertRepository.deleteById(id);

    }

    @Override
    public List<Dessert> findAll() {
        return dessertRepository.findAll();
    }

    @Override
    public Optional<Dessert> findById(Integer id) {
        return dessertRepository.findById(id);
    }

    @Override
    public Boolean insert(Dessert t) {
        String nomRecette = t.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            return false;
        }
        dessertRepository.save(t);
        return true;
    }

    @Override
    public Dessert update(Dessert t) {
        Optional<Dessert> opt = dessertRepository.findById(t.getId());
        if (opt.isPresent()) {
            Dessert recetteEnBase = opt.get();
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
            return dessertRepository.save(recetteEnBase);
        } else {
            return null;
        }
    }

    @Override
    public List<Dessert> findAllWithTags() {
        return dessertRepository.findAllWithTags();
    }

    @Override
    public List<Dessert> findByAuteur(Utilisateur auteur) {
        return dessertRepository.findByAuteur(auteur);
    }

    @Override
    public List<Dessert> findByCout(Couts cout) {
        return dessertRepository.findByCout(cout);
    }

    @Override
    public List<Dessert> findByCoutNot(Couts cout) {
        return dessertRepository.findByCoutNot(cout);
    }

    @Override
    public List<Dessert> findByDifficulte(Difficulte difficulte) {
        return dessertRepository.findByDifficulte(difficulte);
    }

    @Override
    public List<Dessert> findByDifficulteNot(Difficulte difficulte) {
        return dessertRepository.findByDifficulteNot(difficulte);
    }

    @Override
    public List<Dessert> findByIngredientContaining(Ingredient ingredient) {
        return dessertRepository.findByIngredients(ingredient);
    }

    @Override
    public List<Dessert> findByIngredientNotContaining(Ingredient ingredient) {
        return dessertRepository.findByIngredientsNot(ingredient);
    }

    @Override
    public List<Dessert> findByTags(Tag tag) {
        return dessertRepository.findByTags(tag);
    }

    @Override
    public List<Dessert> findByTagsNot(Tag tag) {
        return dessertRepository.findByTagsNot(tag);
    }
}