package projetSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projetSpringBoot.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findById(Integer id);

	Optional<Utilisateur> findByPseudo(String pseudo);

	void deleteById(Integer id);

	@Query("select u from Utilisateur u left join fetch u.roles where u.pseudo=:pseudo")
	public Optional<Utilisateur> findByUsernameWithRoles(String pseudo);
}
