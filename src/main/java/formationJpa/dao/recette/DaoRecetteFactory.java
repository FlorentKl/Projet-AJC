package formationJpa.dao.recette;

public class DaoRecetteFactory {
	private static DaoRecette singleton = null;

	public static DaoRecette getInstance() {
		if (singleton == null) {
			singleton = new DaoRecetteJpaImpl();
		}
		return singleton;
	}

	private DaoRecetteFactory() {

	}

}
