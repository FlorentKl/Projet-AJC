package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

public interface GenericInterfaceService<T, I> {
    /**
     * @return Return true si insert OK, sinon false
     */
    T insert(T t);

    T update(T t);

    List<T> findAll();

    void delete(T t);

    void deleteById(I id);

    Optional<T> findById(I id);
}