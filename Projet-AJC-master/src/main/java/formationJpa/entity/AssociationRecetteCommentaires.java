package formationJpa.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import formationJpa.entity.recette.Recette;

@Entity
@Table(name = "comment")
public class AssociationRecetteCommentaires {
	@Column(name = "text")
	private String texte;
	@EmbeddedId
	private AssociationRecetteCommentaireKey id;
	@Column(name = "notation")
	private Integer note;

	
	public AssociationRecetteCommentaires(String texte, AssociationRecetteCommentaireKey id, Integer note) {
		this.texte = texte;
		this.id = id;
		this.note = note;
	}

	public AssociationRecetteCommentaires() {
		
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}


	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public AssociationRecetteCommentaireKey getId() {
		return id;
	}

	public void setId(AssociationRecetteCommentaireKey id) {
		this.id = id;
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
		AssociationRecetteCommentaires other = (AssociationRecetteCommentaires) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
