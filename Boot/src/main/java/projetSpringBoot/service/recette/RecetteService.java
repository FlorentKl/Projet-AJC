package projetSpringBoot.service.recette;

import java.util.List;
import java.util.Optional;

import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.service.GenericInterfaceService;

public interface RecetteService extends GenericInterfaceService<Recette, Integer>, GenericRecetteService<Recette> {
    Boolean insert(Recette recette);

    // Find All
    List<Recette> findAll();

    // findById
    Optional<Recette> findById(Integer id);

    // findByNom
    List<Recette> findRecetteByNom(String nom);
}