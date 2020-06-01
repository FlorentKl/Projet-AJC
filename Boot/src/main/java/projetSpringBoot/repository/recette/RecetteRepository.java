package projetSpringBoot.repository.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<T> findByNom(String nom);

    @Transactional
    List<T> findByNomContainingIgnoreCase(String nom);

    @Transactional
    List<T> findByNomNotContainingIgnoreCase(String nom);

    List<T> findByIngredients(Ingredient ingredient);

    List<T> findByIngredientsNot(Ingredient ingredient);

    List<T> findByTags(Tag tag);

    List<T> findByTagsNot(Tag tag);

    List<T> findByAuteur(Utilisateur auteur);

    List<T> findByDifficulte(Difficulte difficulte);

    List<T> findByDifficulteNot(Difficulte difficulte);

    List<T> findByCout(Couts cout);

    List<T> findByCoutNot(Couts cout);

    // ADDED
    @Query("select distinct r from Recette r left join fetch r.tags")
    List<T> findAllWithTags();

    // TODO List<T> findByNote()
}
