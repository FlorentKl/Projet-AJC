package formationJpa.entity.recette;

public enum Couts {
	L("low"), M("medium"), E("expensive"),VE("very_expensive");

	private String label;

	private Couts(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
