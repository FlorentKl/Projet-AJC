package formationJpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.AssociationRecetteCommentaire;
import formationJpa.entity.AssociationRecetteCommentaireKey;

public interface AssociationRecetteCommentaireRepository extends JpaRepository<AssociationRecetteCommentaire, AssociationRecetteCommentaireKey> {
    Optional<AssociationRecetteCommentaire> findById(AssociationRecetteCommentaireKey id);

}
