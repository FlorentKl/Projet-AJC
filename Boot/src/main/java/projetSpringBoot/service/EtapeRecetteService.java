package projetSpringBoot.service;

import java.util.Optional;

import projetSpringBoot.exception.NoEtapeRecetteException;
import projetSpringBoot.model.recette.EtapeRecette;

public interface EtapeRecetteService {
    void insert(EtapeRecette etapeRecette) throws NoEtapeRecetteException;

    EtapeRecette update(EtapeRecette etapeRecette);

    void delete(EtapeRecette etapeRecette);

    void deleteById(Integer id);

    Optional<EtapeRecette> findById(Integer id);
}