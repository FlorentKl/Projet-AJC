package projetSpringBoot.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.UtilisateurRole;

public class UtilisateurDetails implements UserDetails {

	private Utilisateur utilisateur;
	
	public UtilisateurDetails (Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (UtilisateurRole utilisateurRole : utilisateur.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(utilisateurRole.getRole().toString()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		return utilisateur.getPseudo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return utilisateur.getEnabled();
	}

}
