package projetSpringBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Utilisateur;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	UtilisateurService utilisateurService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> opt = utilisateurService.findByUsernameWithRoles(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("Utilisateur inconnu");
		}
		UtilisateurDetails user = new UtilisateurDetails(opt.get());
		return user;
	}

}
