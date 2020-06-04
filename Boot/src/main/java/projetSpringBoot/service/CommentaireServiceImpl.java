package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.CommentaireKey;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.repository.CommentaireRepository;

@Service
public class CommentaireServiceImpl implements CommentaireService {

	@Autowired
	private CommentaireRepository commentaireRepository;

	public Commentaire insert(Commentaire t) {
		if (t.getNote() < 0 || t.getNote() > 10) {
			return t;
		}
		return commentaireRepository.save(t);

	}

	public Commentaire update(Commentaire commentaire) {
		Optional<Commentaire> opt = commentaireRepository.findById(commentaire.getId());
		if (opt.isPresent()) {
			Commentaire commEnBase = opt.get();

			commEnBase.setNote(commentaire.getNote());
			commEnBase.setTexte(commentaire.getTexte());

			return commentaireRepository.save(commEnBase);
		} else {
			// salleRepository.save(salle);// on insert
			return null;
		}
	}

	public void delete(Commentaire commentaire) {
		commentaireRepository.delete(commentaire);
	}

	public void deleteById(CommentaireKey id) {
		Optional<Commentaire> opt = commentaireRepository.findById(id);
		if (opt.isPresent()) {
			deleteById(id);
		} else {
			throw new IllegalArgumentException();
		}

	}

	public List<Commentaire> findAll() {
		return commentaireRepository.findAll();
	}

	public Optional<Commentaire> findById(CommentaireKey id) {
		return commentaireRepository.findById(id);

	}

	@Override
	public List<Commentaire> findByRecette(Integer id) {
		return commentaireRepository.findByRecette(id);
	}

}
