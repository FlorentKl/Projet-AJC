package formationJpa.dao.associationRecetteCommentaire;

public class DaoAssociationRecetteCommentaireFactory {
	private static DaoAssociationRecetteCommentaire singleton = null;

	public static DaoAssociationRecetteCommentaire getInstance() {
		if (singleton == null) {
			singleton = new DaoAssociationRecetteCommentaireJpaImpl();
		}
		return singleton;
	}

	private DaoAssociationRecetteCommentaireFactory() {

	}

}
