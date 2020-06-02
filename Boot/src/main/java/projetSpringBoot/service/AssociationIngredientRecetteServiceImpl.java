package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Ingredients.AssociationIngredientRecette;
import projetSpringBoot.model.Ingredients.AssociationIngredientRecetteKey;
import projetSpringBoot.repository.AssociationIngredientRecetteRepository;

@Service
public class AssociationIngredientRecetteServiceImpl implements AssociationIngredientRecetteService {

	@Autowired
	private AssociationIngredientRecetteRepository associationIngredientRecetteRepository;

	public AssociationIngredientRecette insert(AssociationIngredientRecette t) {
		if (t.getQuantite() < 0) {
			t.setQuantite(0);
		}
		return associationIngredientRecetteRepository.save(t);

	}

	public AssociationIngredientRecette update(AssociationIngredientRecette associationIngredientRecette) {
		Optional<AssociationIngredientRecette> opt = associationIngredientRecetteRepository
				.findById(associationIngredientRecette.getId());
		if (opt.isPresent()) {
			return associationIngredientRecetteRepository.save(opt.get());
		} else {
			return null;
		}
	}

	public void delete(AssociationIngredientRecette associationIngredientRecette) {
		associationIngredientRecetteRepository.delete(associationIngredientRecette);
	}

	public void deleteById(AssociationIngredientRecetteKey id) {
		Optional<AssociationIngredientRecette> opt = associationIngredientRecetteRepository.findById(id);
		if (opt.isPresent()) {
			deleteById(id);
		} else {
			throw new IllegalArgumentException();
		}

	}

	public List<AssociationIngredientRecette> findAll() {
		return associationIngredientRecetteRepository.findAll();
	}

	public Optional<AssociationIngredientRecette> findById(AssociationIngredientRecetteKey id) {
		return associationIngredientRecetteRepository.findById(id);
	}
}
