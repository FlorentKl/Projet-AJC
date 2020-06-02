package projetSpringBoot.model.Ingredients;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "recipe_ingredients")
public class AssociationIngredientRecette {
	@JsonView(value = { Views.RecetteWithAll.class, Views.IngredientView.class })
	private Integer quantite;

	@JsonView(value = { Views.RecetteWithAll.class, Views.IngredientView.class })
	@EmbeddedId
	private AssociationIngredientRecetteKey id;

	@JsonView(value = { Views.RecetteWithAll.class, Views.IngredientView.class })
	@Column(name = "units", length = 2)
	@Enumerated(EnumType.STRING)
	private Unite unite;

	@JsonView(value = { Views.RecetteWithAll.class, Views.IngredientView.class })
	@Version
	private Integer version;

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

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public AssociationIngredientRecetteKey getId() {
		return id;
	}

	public void setId(AssociationIngredientRecetteKey id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
