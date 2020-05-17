package projetSpringBoot.model.Ingredients;

public enum Masse {
	
	G("gramme"), KG("kilogramme"), MG("milligramme");

	private String label;

	private Masse(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
