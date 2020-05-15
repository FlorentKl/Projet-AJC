package projetSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.entity.tag.AssociationTagRecette;
import projetSpringBoot.entity.tag.AssociationTagRecetteKey;

public interface AssociationTagRecetteRepository extends JpaRepository<AssociationTagRecette, AssociationTagRecetteKey> {

}
