package formationJpa.dao.associationIngredientRecette;

public class DaoAssociationIngredientRecetteFactory {
	private static DaoAssociationIngredientRecette singleton = null;

	public static DaoAssociationIngredientRecette getInstance() {
		if (singleton == null) {
			singleton = new DaoAssociationIngredientRecetteJpaImpl();
		}
		return singleton;
	}

	private DaoAssociationIngredientRecetteFactory() {

	}

}
