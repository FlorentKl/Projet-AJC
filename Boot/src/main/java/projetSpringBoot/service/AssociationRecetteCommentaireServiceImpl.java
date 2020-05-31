package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.AssociationRecetteCommentaire;
import projetSpringBoot.model.AssociationRecetteCommentaireKey;
import projetSpringBoot.repository.AssociationRecetteCommentaireRepository;

@Service
public class AssociationRecetteCommentaireServiceImpl implements AssociationRecetteCommentaireService {

	@Autowired
	private AssociationRecetteCommentaireRepository associationRecetteCommentaireRepository;

	public AssociationRecetteCommentaire insert(AssociationRecetteCommentaire t) {
		if (t.getNote() < 0 || t.getNote() > 10) {
			return t;
		}
		return associationRecetteCommentaireRepository.save(t);

	}

	public AssociationRecetteCommentaire update(AssociationRecetteCommentaire associationRecetteCommentaire) {
		Optional<AssociationRecetteCommentaire> opt = associationRecetteCommentaireRepository
				.findById(associationRecetteCommentaire.getId());
		if (opt.isPresent()) {
			AssociationRecetteCommentaire associationEnBase = opt.get();

			associationEnBase.setNote(associationRecetteCommentaire.getNote());
			associationEnBase.setTexte(associationRecetteCommentaire.getTexte());

			return associationRecetteCommentaireRepository.save(associationEnBase);
		} else {
			// salleRepository.save(salle);// on insert
			return null;
		}
	}

	public void delete(AssociationRecetteCommentaire associationRecetteCommentaire) {
		associationRecetteCommentaireRepository.delete(associationRecetteCommentaire);
	}

	public void deleteById(AssociationRecetteCommentaireKey id) {
		Optional<AssociationRecetteCommentaire> opt = associationRecetteCommentaireRepository.findById(id);
		if (opt.isPresent()) {
			deleteById(id);
		} else {
			throw new IllegalArgumentException();
		}

	}

	public List<AssociationRecetteCommentaire> findAll() {
		return associationRecetteCommentaireRepository.findAll();
	}

	public Optional<AssociationRecetteCommentaire> findById(AssociationRecetteCommentaireKey id) {
		return associationRecetteCommentaireRepository.findById(id);

	}
}
