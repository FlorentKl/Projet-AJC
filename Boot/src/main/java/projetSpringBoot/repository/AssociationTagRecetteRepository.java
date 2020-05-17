package projetSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.model.tag.AssociationTagRecette;
import projetSpringBoot.model.tag.AssociationTagRecetteKey;

public interface AssociationTagRecetteRepository extends JpaRepository<AssociationTagRecette, AssociationTagRecetteKey> {

}
