package projetSpringBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.entity.AssociationRecetteCommentaire;
import projetSpringBoot.entity.AssociationRecetteCommentaireKey;
import projetSpringBoot.repository.AssociationRecetteCommentaireRepository;

@Service
public class AssociationRecetteCommentaireService {
	// TODO - ajout
	// TODO - mise � jour
	// TODO - delete
	// TODO - deleteByKey
	// TODO - findById
	
	@Autowired
	private  AssociationRecetteCommentaireRepository associationRecetteCommentaireRepository;
	
	public void ajout(AssociationRecetteCommentaire associationRecetteCommentaire) {
		if (associationRecetteCommentaire.getNote()<0 || associationRecetteCommentaire.getNote()>10) {
			throw new IllegalArgumentException();
		}
		associationRecetteCommentaireRepository.save(associationRecetteCommentaire);
	
	}


	
	public AssociationRecetteCommentaire update(AssociationRecetteCommentaire associationRecetteCommentaire) {
		Optional<AssociationRecetteCommentaire> opt = associationRecetteCommentaireRepository.findById(associationRecetteCommentaire.getId());
		if (opt.isPresent()) {
			AssociationRecetteCommentaire ingredientRecette = opt.get();
			
			associationRecetteCommentaire.setNote(associationRecetteCommentaire.getNote());
			
			
			associationRecetteCommentaire.setTexte(associationRecetteCommentaire.getTexte());
			associationRecetteCommentaireRepository.save(associationRecetteCommentaire);
			return associationRecetteCommentaire;
		} else {
			// salleRepository.save(salle);// on insert
			return null;
		}
	}
	
	public void delete(AssociationRecetteCommentaire associationRecetteCommentaire) {
		associationRecetteCommentaireRepository.delete(associationRecetteCommentaire);
	}
	
        public void deleteById(AssociationRecetteCommentaireKey id) {
		Optional<AssociationRecetteCommentaire> opt=associationRecetteCommentaireRepository.findById(id);
		if(opt.isPresent()) {
			deleteById(id);
		}else {
			throw new IllegalArgumentException();
		}
		
		
	}
	
        public AssociationRecetteCommentaire recherche(AssociationRecetteCommentaireKey id) {
		Optional<AssociationRecetteCommentaire> opt=associationRecetteCommentaireRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new IllegalArgumentException();
		}
		
		
	}
}
