package formationJpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formationJpa.entity.tag.Tag;
import formationJpa.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public void add(Tag tag) {
		tagRepository.save(tag);
	}

	public Tag update(Tag tag) {
		Optional<Tag> opt = tagRepository.findById(tag.getId());
		if (opt.isPresent()) {
			Tag tagEnBase = opt.get();
			tagEnBase.setRecettes(tag.getRecettes());
			tagRepository.save(tagEnBase);
			return tagEnBase;
		} else {
			tagRepository.save(tag);
			return tag;
		}
	}

	public void delete(Tag tag) {
		tagRepository.delete(tag);
	}
	
	public void deleteById(Integer id) {
		tagRepository.deleteById(id);
	}

	public List<Tag> findAll(){
		return tagRepository.findAll();
	}
	
	public Optional<Tag> findByTag(String tag) {
		return tagRepository.findByTag(tag);
	}
	
	public List<Tag> findByTagContaining(String tag){
		return tagRepository.findByTagContaining(tag);
	}
	
	public Optional<Tag> findById(Integer id) {
		return tagRepository.findById(id);
	}

}
