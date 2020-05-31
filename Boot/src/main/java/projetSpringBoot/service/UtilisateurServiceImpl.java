package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	public Utilisateur insert(Utilisateur utilisateur) {
		Optional<Utilisateur> opt = utilisateurRepository.findByPseudo(utilisateur.getPseudo());
		if (!opt.isPresent()) {
			return utilisateurRepository.save(utilisateur);
		}
		return utilisateur;
	}

	public Utilisateur update(Utilisateur utilisateur) {
		Optional<Utilisateur> opt = utilisateurRepository.findById(utilisateur.getId());
		if (opt.isPresent()) {
			Utilisateur utilisateurEnBase = opt.get();
			if (utilisateur.getPseudo() != null) {
				utilisateurEnBase.setPseudo(utilisateur.getPseudo());
			}
			if (utilisateur.getPassword() != null) {
				utilisateurEnBase.setPassword(utilisateur.getPassword());
			}
			utilisateurEnBase.setRecette(utilisateur.getRecette());
			utilisateurEnBase.setCommentaires(utilisateur.getCommentaires());
			utilisateurRepository.save(utilisateurEnBase);
			return utilisateurEnBase;
		} else {
			return null;
		}
	}

	public Optional<Utilisateur> findById(Integer id) {
		return utilisateurRepository.findById(id);
	}

	public Optional<Utilisateur> findByPseudo(String pseudo) {
		return utilisateurRepository.findByPseudo(pseudo);
	}

	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
	}

	public void delete(Utilisateur utilisateur) {
		utilisateurRepository.delete(utilisateur);
	}

	public void deleteById(Integer id) {
		utilisateurRepository.deleteById(id);
	}

}
