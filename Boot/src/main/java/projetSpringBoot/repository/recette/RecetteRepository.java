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

    @Transactional
    Optional<T> findById(Integer id);

    @Transactional
    List<T> findAll();

    @Transactional
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
    @Query("SELECT DISTINCT r FROM Recette r LEFT JOIN FETCH r.tags")
    List<T> findAllWithTags();

    // @Query(value = "SELECT * FROM Public.recipe as r WHERE EXISTS (SELECT
    // recette, AVG(notation) as truc FROM Public.comment c WHERE r.id=c.recette
    // GROUP BY recette HAVING Avg(notation)>=5)", nativeQuery = true)
    @Transactional
    @Query("SELECT r FROM #{#entityName} r WHERE EXISTS (SELECT c.id.recette, AVG(c.note) FROM Commentaire c WHERE c.id.recette=r GROUP BY c.id.recette HAVING AVG(c.note) >=:note)")
    List<T> findByNote(@Param("note") Double note);
}
