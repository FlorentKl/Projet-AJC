package projetSpringBoot.entity.tag;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import projetSpringBoot.entity.recette.Recette;

@Entity
@Table(name = "tag_recette")
public class AssociationTagRecette {
	@EmbeddedId
	private AssociationTagRecetteKey id;
	
	public AssociationTagRecette() {
		
	}
	

	public AssociationTagRecetteKey getId() {
		return id;
	}

	public void setId(AssociationTagRecetteKey id) {
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
		AssociationTagRecette other = (AssociationTagRecette) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
