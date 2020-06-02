package projetSpringBoot.repository.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.tag.Tag;

public interface RecetteRepository<T extends Recette> extends JpaRepository<T, Integer> {

    Optional<T> findById(Integer id);

    List<T> findAll();

    Optional<T> findByNom(String nom);

    @Transactional
    List<T> findByNomContainingIgnoreCase(String nom);

    @Transactional
    List<T> findByNomNotContainingIgnoreCase(String nom);

    @Transactional
    List<T> findByIngredients(Ingredient ingredient);

    @Transactional
    List<T> findByIngredientsNot(Ingredient ingredient);

    @Transactional
    List<T> findByTags(Tag tag);

    @Transactional
    List<T> findByTagsNot(Tag tag);

    @Transactional
    List<T> findByAuteur(Utilisateur auteur);

    @Transactional
    List<T> findByDifficulte(Difficulte difficulte);

    @Transactional
    List<T> findByDifficulteNot(Difficulte difficulte);

    @Transactional
    List<T> findByCout(Couts cout);

    @Transactional
    List<T> findByCoutNot(Couts cout);

    // ADDED
    @Query("select distinct r from Recette r left join fetch r.tags")
    List<T> findAllWithTags();

    // TODO List<T> findByNote()
}
