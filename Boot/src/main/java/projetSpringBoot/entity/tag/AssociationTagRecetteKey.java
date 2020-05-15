package projetSpringBoot.entity.tag;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import projetSpringBoot.entity.recette.Recette;
@Embeddable
public class AssociationTagRecetteKey implements Serializable{
	@ManyToOne
	@JoinColumn(name = "recette", foreignKey = @ForeignKey(name = "recette_tag_recette_fk"))
	private Recette recette;
	@ManyToOne
	@JoinColumn(name = "tag", foreignKey = @ForeignKey(name = "recette_tag_tag_fk"))
	private Tag tag ;
	
	public AssociationTagRecetteKey() {
		
	}

	public AssociationTagRecetteKey(Recette recette, Tag tag) {
		this.recette = recette;
		this.tag = tag;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recette == null) ? 0 : recette.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		AssociationTagRecetteKey other = (AssociationTagRecetteKey) obj;
		if (recette == null) {
			if (other.recette != null)
				return false;
		} else if (!recette.equals(other.recette))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}
	
	
}
