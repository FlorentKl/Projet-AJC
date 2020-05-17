package projetSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.model.recette.EtapeRecette;

public interface EtapeRecetteRepository extends JpaRepository<EtapeRecette, Integer> {

}
