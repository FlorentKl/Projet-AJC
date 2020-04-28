package formationJpa.dao.etape;

public class DaoEtapeFactory {
	private static DaoEtape singleton = null;

	public static DaoEtape getInstance() {
		if (singleton == null) {
			singleton = new DaoEtapeJpaImpl();
		}
		return singleton;
	}

	private DaoEtapeFactory() {

	}

}
