package formationJpa.entity.recette;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import formationJpa.entity.AssociationRecetteCommentaires;
import formationJpa.entity.Utilisateur;
import formationJpa.entity.Ingredients.AssociationIngredientsRecette;
import formationJpa.entity.Ingredients.Ingredient;
import formationJpa.entity.tag.AssociationTagRecette;
import formationJpa.entity.tag.Tag;

@Entity
@Table(name = "Recipe")
@SequenceGenerator(name = "seqRecipe", sequenceName = "seq_recipe", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 1)
public  abstract class Recette {
	@Column(name = "name_recipe",length = 150)
	private String nom;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqRecipe")
	private Integer id;
	@Column(name = "nbperson_recipe",length = 150)
	private Integer nbPersonne;
	@Column(name = "duration_recipe",length = 150)
	private Integer temps;
	@OneToMany(mappedBy = "recette")
	private List<AssociationTagRecette> tags;
	@OneToMany(mappedBy = "recette")
	private List<AssociationIngredientsRecette> ingredients;
	@OneToMany(mappedBy ="id_recette")
	private List<EtapeRecette> etapes;
	@OneToMany(mappedBy ="commentaires")
	private List<AssociationRecetteCommentaires>  commentaires;
	@ManyToOne
	@JoinColumn(name = "auteur", foreignKey = @ForeignKey(name = "recette_auteur_fk"))
	private Utilisateur auteur;
	@Column(name = "cost", length = 2)
	@Enumerated(EnumType.STRING)
	private Couts cout;
	@Column(name = "difficulte", length = 2)
	@Enumerated(EnumType.STRING)
	private Difficulte difficulte;
	
	
	public Recette() {
		
	}

	public Recette(String nom, Integer id, Integer nbPersonne, Integer temps) {
		this.nom = nom;
		this.id = id;
		this.nbPersonne = nbPersonne;
		this.temps = temps;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(Integer nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public Integer getTemps() {
		return temps;
	}

	public void setTemps(Integer temps) {
		this.temps = temps;
	}

	public List<AssociationTagRecette> getTags() {
		return tags;
	}

	public void setTags(List<AssociationTagRecette> tags) {
		this.tags = tags;
	}

	public List<AssociationIngredientsRecette> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<AssociationIngredientsRecette> ingredients) {
		this.ingredients = ingredients;
	}

	public List<EtapeRecette> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<EtapeRecette> etapes) {
		this.etapes = etapes;
	}

	public List<AssociationRecetteCommentaires> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<AssociationRecetteCommentaires> commentaires) {
		this.commentaires = commentaires;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Couts getCout() {
		return cout;
	}

	public void setCout(Couts cout) {
		this.cout = cout;
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
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
		Recette other = (Recette) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

