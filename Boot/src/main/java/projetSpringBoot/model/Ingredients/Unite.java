package projetSpringBoot.model.Ingredients;

public enum Unite {
    G("gramme"), KG("kilogramme"), MG("milligramme"), ML("mililitre"), CL("centilitre"), L("litre");

    private String label;

    private Unite(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}