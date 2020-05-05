package formationJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.tag.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{

}
