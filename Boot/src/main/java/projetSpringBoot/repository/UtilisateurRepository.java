package projetSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import projetSpringBoot.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	@Transactional
	Optional<Utilisateur> findById(Integer id);

	@Transactional
	Optional<Utilisateur> findByPseudo(String pseudo);

	void deleteById(Integer id);

	@Query("SELECT u FROM Utilisateur u LEFT JOIN FETCH u.roles WHERE u.pseudo=:pseudo")
	Optional<Utilisateur> findByUsernameWithRoles(String pseudo);
}
