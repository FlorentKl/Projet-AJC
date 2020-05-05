package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.recette.Recette;

public interface RecetteRepository extends JpaRepository<Recette, Integer>{

}
