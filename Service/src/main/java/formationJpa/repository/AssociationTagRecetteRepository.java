package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.tag.AssociationTagRecette;
import formationJpa.entity.tag.AssociationTagRecetteKey;

public interface AssociationTagRecetteRepository extends JpaRepository<AssociationTagRecette, AssociationTagRecetteKey> {

}
