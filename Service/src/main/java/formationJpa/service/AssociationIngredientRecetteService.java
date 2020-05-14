package formationJpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formationJpa.entity.Ingredients.AssociationIngredientRecette;
import formationJpa.entity.Ingredients.AssociationIngredientRecetteKey;
import formationJpa.repository.AssociationIngredientRecetteRepository;

@Service
public class AssociationIngredientRecetteService {
	// TODO - ajout
	// TODO - mise à jour
	// TODO - delete
	// TODO - deleteByKey
	// TODO - findById
	
	@Autowired
	private  AssociationIngredientRecetteRepository associationIngredientRecetteRepository;
	
	public void insert(AssociationIngredientRecette associationIngredientRecette) {
		
		
		if (associationIngredientRecette.getQuantite()<0) {
			throw new IllegalArgumentException();
		}
		
		if (associationIngredientRecette.getMesure_liquide()==null && associationIngredientRecette.getMesure_solide()==null) {
			throw new IllegalArgumentException();
		}
		
		if (associationIngredientRecette.getMesure_liquide()!=null && associationIngredientRecette.getMesure_solide()!=null) {
			throw new IllegalArgumentException();
		}
		associationIngredientRecetteRepository.save(associationIngredientRecette);
	
	}

	public AssociationIngredientRecette update(AssociationIngredientRecette associationIngredientRecette) {
		Optional<AssociationIngredientRecette> opt = associationIngredientRecetteRepository.findById(associationIngredientRecette.getId());
		if (opt.isPresent()) {
			AssociationIngredientRecette ingredientRecette = opt.get();
			if (associationIngredientRecette.getMesure_liquide() != null) {
				ingredientRecette.setQuantite(associationIngredientRecette.getQuantite());
			}
			if (associationIngredientRecette.getMesure_solide() != null) {
				ingredientRecette.setQuantite(associationIngredientRecette.getQuantite());
			}
			associationIngredientRecetteRepository.save(associationIngredientRecette);
			return ingredientRecette;
		} else {
			// salleRepository.save(salle);// on insert
			return null;
		}
	}
	
	public void delete(AssociationIngredientRecette associationIngredientRecette) {
		associationIngredientRecetteRepository.delete(associationIngredientRecette);
	}
	
	
        public void deleteById(AssociationIngredientRecetteKey id) {
		Optional<AssociationIngredientRecette> opt=associationIngredientRecetteRepository.findById(id);
		if(opt.isPresent()) {
			deleteById(id);
		}else {
			throw new IllegalArgumentException();
		}
		
	}
	
        public AssociationIngredientRecette searchById(AssociationIngredientRecetteKey id) {
		Optional<AssociationIngredientRecette> opt=associationIngredientRecetteRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new IllegalArgumentException();
		}
	}
}
