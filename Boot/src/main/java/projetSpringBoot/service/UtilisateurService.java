package projetSpringBoot.service;

import java.util.Optional;

import projetSpringBoot.model.Utilisateur;

public interface UtilisateurService extends GenericInterfaceService<Utilisateur, Integer> {
    Optional<Utilisateur> findByPseudo(String pseudo);

    Optional<Utilisateur> findByUsernameWithRoles(String pseudo);
}