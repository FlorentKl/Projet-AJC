package test;

import formationJpa.context.Context;
import formationJpa.dao.utilisateur.DaoUtilisateur;
import formationJpa.dao.utilisateur.DaoUtilisateurFactory;
import formationJpa.entity.Utilisateur;

public class AppTest {

    public static void main(String[] args) {
        DaoUtilisateur daoUtilisateur = DaoUtilisateurFactory.getInstance();
        Utilisateur machin = new Utilisateur("machin", "bidule");
        daoUtilisateur.insert(machin);
        daoUtilisateur.delete(machin);
        
        System.out.println("----");
        Context.destroy();
    }

}
