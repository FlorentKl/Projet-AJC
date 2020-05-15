package projetSpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.entity.tag.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	Optional<Tag> findById(Integer id);

	Optional<Tag> findByTag(String tag);
	
	List<Tag> findByTagContaining(String tag);

	void deleteById(Integer id);
}
