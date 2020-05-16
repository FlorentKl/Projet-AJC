package projetSpringBoot.repository.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projetSpringBoot.entity.Utilisateur;
import projetSpringBoot.entity.Ingredients.Ingredient;
import projetSpringBoot.entity.recette.Couts;
import projetSpringBoot.entity.recette.Difficulte;
import projetSpringBoot.entity.recette.Recette;
import projetSpringBoot.entity.tag.Tag;

public interface RecetteRepository<T extends Recette> extends JpaRepository<T, Integer> {

    Optional<T> findById(Integer id);

    List<T> findAll();

    List<T> findByNom(String nom);

    List<T> findByNomLike(String nom);

    List<T> findByNomNotLike(String nom);

    List<T> findByIngredients(Ingredient ingredient);

    List<T> findByIngredientsNot(Ingredient ingredient);

    List<T> findByTags(Tag tag);

    List<T> findByTagsNot(Tag tag);

    List<T> findByAuteur(Utilisateur auteur);

    List<T> findByDifficulte(Difficulte difficulte);

    List<T> findByDifficulteNot(Difficulte difficulte);

    List<T> findByCout(Couts cout);

    List<T> findByCoutNot(Couts cout);

    //ADDED
    @Query("select distinct r from Recette r left join fetch r.tags")
	List<Recette> findAllWithTags();
    
    
    // TODO List<T> findByNote()
}