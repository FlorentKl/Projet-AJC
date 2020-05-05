package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.AssociationRecetteCommentaire;
import formationJpa.entity.AssociationRecetteCommentaireKey;

public interface AssociationRecetteCommentaireRepository extends JpaRepository<AssociationRecetteCommentaire, AssociationRecetteCommentaireKey> {

}
