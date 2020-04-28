package formationJpa.entity.recette;

public enum Difficulte {
	F("facile"), M("medium"), D("difficile"),E("expert");

	private String label;

	private Difficulte(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
