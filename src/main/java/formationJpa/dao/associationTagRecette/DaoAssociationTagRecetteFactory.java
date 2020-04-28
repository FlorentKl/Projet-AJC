package formationJpa.dao.associationTagRecette;

public class DaoAssociationTagRecetteFactory {
	private static DaoAssociationTagRecette singleton = null;

	public static DaoAssociationTagRecette getInstance() {
		if (singleton == null) {
			singleton = new DaoAssociationTagRecetteJpaImpl();
		}
		return singleton;
	}

	private DaoAssociationTagRecetteFactory() {

	}

}
