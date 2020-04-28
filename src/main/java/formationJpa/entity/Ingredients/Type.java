package formationJpa.entity.Ingredients;

public enum Type {
	L("liquide"), S("solide");

	private String label;

	private Type(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}


}
