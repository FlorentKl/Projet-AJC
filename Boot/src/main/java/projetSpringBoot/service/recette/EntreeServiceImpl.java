package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Entree;
import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.repository.recette.EntreeRepository;

@Service
public class EntreeServiceImpl implements EntreeService {

    @Autowired
    EntreeRepository entreeRepository;

    @Override
    public void delete(Entree t) {
        entreeRepository.delete(t);

    }

    @Override
    public void deleteById(Integer id) {
        entreeRepository.deleteById(id);

    }

    @Override
    public List<Entree> findAll() {
        return entreeRepository.findAll();
    }

    @Override
    public Optional<Entree> findById(Integer id) {
        return entreeRepository.findById(id);
    }

    @Override
    public Entree insert(Entree t) {
        String nomRecette = t.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            return t;
        }
        return entreeRepository.save(t);
    }

    @Override
    public Entree update(Entree t) {
        Optional<Entree> opt = entreeRepository.findById(t.getId());
        if (opt.isPresent()) {
            Entree recetteEnBase = opt.get();
            if (t.getNom() != null) {
                recetteEnBase.setNom(t.getNom());
            }
            recetteEnBase.setAuteur(t.getAuteur());
            recetteEnBase.setCout(t.getCout());
            recetteEnBase.setDifficulte(t.getDifficulte());
            recetteEnBase.setNbPersonne(t.getNbPersonne());
            recetteEnBase.setTags(t.getTags());
            recetteEnBase.setTemps(t.getTemps());
            recetteEnBase.setCommentaires(t.getCommentaires());
            recetteEnBase.setEtapes(t.getEtapes());
            return entreeRepository.save(recetteEnBase);
        } else {
            return null;
        }
    }

    @Override
    public List<Entree> findAllWithTags() {
        return entreeRepository.findAllWithTags();
    }

    @Override
    public List<Entree> findByAuteur(Utilisateur auteur) {
        return entreeRepository.findByAuteur(auteur);
    }

    @Override
    public List<Entree> findByCout(Couts cout) {
        return entreeRepository.findByCout(cout);
    }

    @Override
    public List<Entree> findByCoutNot(Couts cout) {
        return entreeRepository.findByCoutNot(cout);
    }

    @Override
    public List<Entree> findByDifficulte(Difficulte difficulte) {
        return entreeRepository.findByDifficulte(difficulte);
    }

    @Override
    public List<Entree> findByDifficulteNot(Difficulte difficulte) {
        return entreeRepository.findByDifficulteNot(difficulte);
    }

    @Override
    public List<Entree> findByIngredientContaining(Ingredient ingredient) {
        return entreeRepository.findByIngredients(ingredient);
    }

    @Override
    public List<Entree> findByIngredientNotContaining(Ingredient ingredient) {
        return entreeRepository.findByIngredientsNot(ingredient);
    }

    @Override
    public List<Entree> findByTags(Tag tag) {
        return entreeRepository.findByTags(tag);
    }

    @Override
    public List<Entree> findByTagsNot(Tag tag) {
        return entreeRepository.findByTagsNot(tag);
    }

}