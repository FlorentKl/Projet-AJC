package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import projetSpringBoot.exception.NoEtapeRecetteException;

public interface GenericInterfaceService<T, I> {
    /**
     * @return Return true si insert OK, sinon false
     */
    Boolean insert(T t) throws NoEtapeRecetteException;

    T update(T t);

    List<T> findAll();

    void delete(T t);

    void deleteById(I id);

    Optional<T> findById(I id);
}