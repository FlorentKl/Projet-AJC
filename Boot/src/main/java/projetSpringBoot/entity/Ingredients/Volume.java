package projetSpringBoot.entity.Ingredients;

public enum Volume {
	ML("mililitre"), CL("centilitre"), L("litre");

	private String label;

	private Volume(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}


}
