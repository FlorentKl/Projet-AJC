package formationJpa.dao.utilisateur;

public class DaoUtilisateurFactory {
	private static DaoUtilisateur singleton = null;

	public static DaoUtilisateur getInstance() {
		if (singleton == null) {
			singleton = new DaoUtilisateurJpaImpl();
		}
		return singleton;
	}

	private DaoUtilisateurFactory() {

	}

}
