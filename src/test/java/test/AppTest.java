package test;

import formationJpa.context.Context;
import formationJpa.dao.associationIngredientRecette.DaoAssociationIngredientRecette;
import formationJpa.dao.associationIngredientRecette.DaoAssociationIngredientRecetteFactory;
import formationJpa.dao.associationRecetteCommentaire.DaoAssociationRecetteCommentaire;
import formationJpa.dao.associationRecetteCommentaire.DaoAssociationRecetteCommentaireFactory;
import formationJpa.dao.associationTagRecette.DaoAssociationTagRecette;
import formationJpa.dao.associationTagRecette.DaoAssociationTagRecetteFactory;
import formationJpa.dao.etape.DaoEtape;
import formationJpa.dao.etape.DaoEtapeFactory;
import formationJpa.dao.ingredient.DaoIngredient;
import formationJpa.dao.ingredient.DaoIngredientFactory;
import formationJpa.dao.recette.DaoRecette;
import formationJpa.dao.recette.DaoRecetteFactory;
import formationJpa.dao.tag.DaoTag;
import formationJpa.dao.tag.DaoTagFactory;
import formationJpa.dao.utilisateur.DaoUtilisateur;
import formationJpa.dao.utilisateur.DaoUtilisateurFactory;

public class AppTest {

    public static void main(String[] args) {
        DaoUtilisateur daoUtilisateur=DaoUtilisateurFactory.getInstance();
        DaoRecette daoRecette=DaoRecetteFactory.getInstance();
        
        DaoTag daoTag=DaoTagFactory.getInstance();
        DaoEtape daoEtape=DaoEtapeFactory.getInstance();
        DaoIngredient daoIngredient=DaoIngredientFactory.getInstance();
        DaoAssociationIngredientRecette daoAssociationIngredientRecette=DaoAssociationIngredientRecetteFactory.getInstance();
        DaoAssociationRecetteCommentaire  daoAssociationRecetteCommentaire= DaoAssociationRecetteCommentaireFactory.getInstance();
        DaoAssociationTagRecette  daoAssociationTagRecette =DaoAssociationTagRecetteFactory.getInstance();
        System.out.println("----");
        Context.destroy();
    }

}
