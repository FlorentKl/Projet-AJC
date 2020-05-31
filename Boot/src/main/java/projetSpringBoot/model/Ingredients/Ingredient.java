package projetSpringBoot.model.Ingredients;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import projetSpringBoot.model.imageModel.ImageModel;

@Entity
@Table(name = "ingredients")
@SequenceGenerator(name = "seqIngredients", sequenceName = "seq_ingredients", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIngredients")
	@Column(name = "id_ingredients")
	private Integer id;

	@Column(name = "name_ingredients", length = 150)
	private String nom;

	@OneToOne
	@JoinColumn(name = "id_img", referencedColumnName = "id_pic", foreignKey = @ForeignKey(name = "ingredient_pic_FK"))
	private ImageModel picture;

	@OneToMany(mappedBy = "id.recette")
	private List<AssociationIngredientRecette> recettes;

	@Column(name = "type", length = 2)
	@Enumerated(EnumType.STRING)
	private Type type;

	public Ingredient() {

	}

	public Ingredient(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ImageModel getPicture() {
		return picture;
	}

	public void setPicture(ImageModel picture) {
		this.picture = picture;
	}

	public List<AssociationIngredientRecette> getRecettes() {
		return recettes;
	}

	public void setRecettes(List<AssociationIngredientRecette> recettes) {
		this.recettes = recettes;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		Ingredient other = (Ingredient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
