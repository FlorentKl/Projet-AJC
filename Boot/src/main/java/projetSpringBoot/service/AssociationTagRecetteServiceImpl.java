package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.tag.AssociationTagRecette;
import projetSpringBoot.model.tag.AssociationTagRecetteKey;
import projetSpringBoot.repository.AssociationTagRecetteRepository;

@Service
public class AssociationTagRecetteServiceImpl implements AssociationTagRecetteService {

	@Autowired
	private AssociationTagRecetteRepository associationTagRecetteRepository;

	public void insert(AssociationTagRecette associationTagRecette) {
		associationTagRecetteRepository.save(associationTagRecette);
	}

	public AssociationTagRecette update(AssociationTagRecette associationTagRecette) {
		return associationTagRecetteRepository.save(associationTagRecette);
	}

	public void delete(AssociationTagRecette associationTagRecette) {
		associationTagRecetteRepository.delete(associationTagRecette);
	}

	public void deleteById(AssociationTagRecetteKey associationTagRecetteKey) {
		associationTagRecetteRepository.deleteById(associationTagRecetteKey);
	}

	public List<AssociationTagRecette> findAll() {
		return associationTagRecetteRepository.findAll();
	}

	public Optional<AssociationTagRecette> findById(AssociationTagRecetteKey associationTagRecetteKey) {
		return associationTagRecetteRepository.findById(associationTagRecetteKey);

	}

}
