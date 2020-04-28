package formationJpa.dao.tag;

public class DaoTagFactory {
	private static DaoTag singleton = null;

	public static DaoTag getInstance() {
		if (singleton == null) {
			singleton = new DaoTagJpaImpl();
		}
		return singleton;
	}

	private DaoTagFactory() {

	}

}
