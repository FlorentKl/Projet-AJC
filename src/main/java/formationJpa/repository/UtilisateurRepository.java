package formationJpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import formationJpa.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findById(Integer id);

	Optional<Utilisateur> findByPseudo(String pseudo);

	void deleteById(Integer id);
	
}
