package projetSpringBoot.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import projetSpringBoot.model.recette.Recette;

@Embeddable
public class AssociationRecetteCommentaireKey implements Serializable {
	@ManyToOne
	@JoinColumn(name = "recette", foreignKey = @ForeignKey(name = "recette_comment_comment_fk"))
	private Recette recette;
	@ManyToOne
	@JoinColumn(name = "auteur", foreignKey = @ForeignKey(name = "comment_auteur_fk"))
	private Utilisateur auteur;

	public AssociationRecetteCommentaireKey() {

	}

	public AssociationRecetteCommentaireKey(Recette recette, Utilisateur auteur) {
		this.recette = recette;
		this.auteur = auteur;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
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
		AssociationRecetteCommentaireKey other = (AssociationRecetteCommentaireKey) obj;
		if (auteur == null) {
			if (other.auteur != null)
				return false;
		} else if (!auteur.equals(other.auteur))
			return false;
		if (recette == null) {
			if (other.recette != null)
				return false;
		} else if (!recette.equals(other.recette))
			return false;
		return true;
	}

}
