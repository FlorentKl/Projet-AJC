package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Plat;
import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.repository.recette.PlatRepository;

@Service
public class PlatServiceImpl implements PlatService {

    @Autowired
    PlatRepository platRepository;

    @Override
    public void delete(Plat t) {
        platRepository.delete(t);

    }

    @Override
    public void deleteById(Integer id) {
        platRepository.deleteById(id);

    }

    @Override
    public List<Plat> findAll() {
        return platRepository.findAll();
    }

    @Override
    public Optional<Plat> findById(Integer id) {
        return platRepository.findById(id);
    }

    @Override
    public Plat insert(Plat t) {
        String nomRecette = t.getNom();
        if (nomRecette == null || nomRecette.isEmpty()) {
            return t;
        }
        return platRepository.save(t);
    }

    @Override
    public Plat update(Plat t) {
        Optional<Plat> opt = platRepository.findById(t.getId());
        if (opt.isPresent()) {
            Plat recetteEnBase = opt.get();
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

            return platRepository.save(recetteEnBase);
        } else {
            return null;
        }
    }

    // findByNom
    @Override
    public Optional<Plat> findByNom(String nom) {
        return platRepository.findByNom(nom);
    }

    // findByNom
    @Override
    public List<Plat> findByNomContaining(String nom) {
        return platRepository.findByNomContainingIgnoreCase(nom);
    }

    // findByNom
    @Override
    public List<Plat> findByNomNotContaining(String nom) {
        return platRepository.findByNomNotContainingIgnoreCase(nom);
    }

    @Override
    public List<Plat> findAllWithTags() {
        return platRepository.findAllWithTags();
    }

    @Override
    public List<Plat> findByAuteur(Utilisateur auteur) {
        return platRepository.findByAuteur(auteur);
    }

    @Override
    public List<Plat> findByCout(Couts cout) {
        return platRepository.findByCout(cout);
    }

    @Override
    public List<Plat> findByCoutNot(Couts cout) {
        return platRepository.findByCoutNot(cout);
    }

    @Override
    public List<Plat> findByDifficulte(Difficulte difficulte) {
        return platRepository.findByDifficulte(difficulte);
    }

    @Override
    public List<Plat> findByDifficulteNot(Difficulte difficulte) {
        return platRepository.findByDifficulteNot(difficulte);
    }

    @Override
    public List<Plat> findByIngredientContaining(Ingredient ingredient) {
        return platRepository.findByIngredients(ingredient);
    }

    @Override
    public List<Plat> findByIngredientNotContaining(Ingredient ingredient) {
        return platRepository.findByIngredientsNot(ingredient);
    }

    @Override
    public List<Plat> findByTags(Tag tag) {
        return platRepository.findByTags(tag);
    }

    @Override
    public List<Plat> findByTagsNot(Tag tag) {
        return platRepository.findByTagsNot(tag);
    }

}