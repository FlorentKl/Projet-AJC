package projetSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.entity.AssociationRecetteCommentaire;
import projetSpringBoot.entity.AssociationRecetteCommentaireKey;

public interface AssociationRecetteCommentaireRepository extends JpaRepository<AssociationRecetteCommentaire, AssociationRecetteCommentaireKey> {
    Optional<AssociationRecetteCommentaire> findById(AssociationRecetteCommentaireKey id);

}
