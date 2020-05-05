package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.recette.EtapeRecette;

public interface EtapeRecetteRepository extends JpaRepository<EtapeRecette, Integer> {

}
