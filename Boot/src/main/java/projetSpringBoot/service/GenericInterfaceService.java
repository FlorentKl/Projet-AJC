package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import projetSpringBoot.exception.NoEtapeRecetteException;

public interface GenericInterfaceService<T, I> {
    void insert(T t) throws NoEtapeRecetteException;

    T update(T t);

    List<T> findAll();

    void delete(T t);

    void deleteById(I id);

    Optional<T> findById(I id);
}