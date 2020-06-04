package projetSpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.CommentaireKey;
import projetSpringBoot.model.recette.Recette;

public interface CommentaireRepository extends JpaRepository<Commentaire, CommentaireKey> {
    Optional<Commentaire> findById(CommentaireKey id);

    @Query("SELECT c FROM Commentaire c WHERE c.id.recette.id=:id")
    List<Commentaire> findByRecette(@Param("id") Integer id);

}
