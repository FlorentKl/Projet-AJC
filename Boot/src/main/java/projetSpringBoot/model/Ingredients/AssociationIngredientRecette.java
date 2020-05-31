package projetSpringBoot.model.Ingredients;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_ingredients")
public class AssociationIngredientRecette {
	private Integer quantite;

	@EmbeddedId
	private AssociationIngredientRecetteKey id;

	@Column(name = "mesure_liquide", length = 2)
	@Enumerated(EnumType.STRING)
	private Volume mesure_liquide;

	@Column(name = "mesure_solide", length = 2)
	@Enumerated(EnumType.STRING)
	private Masse mesure_solide;

	// TODO gerer l'aspect Masse Type et Volume

	public AssociationIngredientRecette() {

	}

	public AssociationIngredientRecette(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public AssociationIngredientRecetteKey getId() {
		return id;
	}

	public void setId(AssociationIngredientRecetteKey id) {
		this.id = id;
	}

	public Volume getMesure_liquide() {
		return mesure_liquide;
	}

	public void setMesure_liquide(Volume mesure_liquide) {
		this.mesure_liquide = mesure_liquide;
	}

	public Masse getMesure_solide() {
		return mesure_solide;
	}

	public void setMesure_solide(Masse mesure_solide) {
		this.mesure_solide = mesure_solide;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AssociationIngredientRecette other = (AssociationIngredientRecette) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
