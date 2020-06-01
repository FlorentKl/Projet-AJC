package projetSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.CommentaireKey;

public interface CommentaireRepository extends JpaRepository<Commentaire, CommentaireKey> {
    Optional<Commentaire> findById(CommentaireKey id);

}
