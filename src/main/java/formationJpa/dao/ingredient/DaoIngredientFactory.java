package formationJpa.dao.ingredient;

public class DaoIngredientFactory {
	private static DaoIngredient singleton = null;

	public static DaoIngredient getInstance() {
		if (singleton == null) {
			singleton = new DaoIngredientJpaImpl();
		}
		return singleton;
	}

	private DaoIngredientFactory() {

	}

}
