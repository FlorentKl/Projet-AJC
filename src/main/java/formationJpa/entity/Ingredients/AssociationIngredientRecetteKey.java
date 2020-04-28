package formationJpa.entity.Ingredients;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import formationJpa.entity.recette.Recette;
@Embeddable
public class AssociationIngredientRecetteKey implements Serializable{
	@ManyToOne
	@JoinColumn(name = "recette", foreignKey = @ForeignKey(name = "recette_ingredient_recette_fk"))
	private Recette recette;
	@ManyToOne
	@JoinColumn(name = "ingredient", foreignKey = @ForeignKey(name = "recette_ingredient_ingredient_fk"))
	private Ingredient ingredient;
	
	public AssociationIngredientRecetteKey() {
		
	}

	public AssociationIngredientRecetteKey(Recette recette, Ingredient ingredient) {
		this.recette = recette;
		this.ingredient = ingredient;
	}


	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((recette == null) ? 0 : recette.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssociationIngredientRecetteKey other = (AssociationIngredientRecetteKey) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (recette == null) {
			if (other.recette != null)
				return false;
		} else if (!recette.equals(other.recette))
			return false;
		return true;
	}

}
