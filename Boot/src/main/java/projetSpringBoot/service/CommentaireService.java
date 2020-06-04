package projetSpringBoot.service;

import java.util.List;

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.CommentaireKey;
import projetSpringBoot.model.recette.Recette;

public interface CommentaireService extends GenericInterfaceService<Commentaire, CommentaireKey> {
    List<Commentaire> findByRecette(Integer id);
}