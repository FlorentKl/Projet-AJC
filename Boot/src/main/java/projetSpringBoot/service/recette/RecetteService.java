package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.service.GenericInterfaceService;

public interface RecetteService extends GenericInterfaceService<Recette, Integer>, GenericRecetteService<Recette> {
    Recette insert(Recette recette);

    // Find All
    @Transactional
    List<Recette> findAll();

    // findById
    Optional<Recette> findById(Integer id);

}