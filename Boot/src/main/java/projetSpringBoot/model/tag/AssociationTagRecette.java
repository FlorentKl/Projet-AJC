package projetSpringBoot.model.tag;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "tag_recette")
public class AssociationTagRecette {
    @JsonView(value = { Views.RecetteWithAll.class, Views.TagView.class })
    @EmbeddedId
    private AssociationTagRecetteKey id;

    @JsonView(value = { Views.RecetteWithAll.class, Views.TagView.class })
    @Version
    private Integer version;

    public AssociationTagRecette() {

    }

    public AssociationTagRecetteKey getId() {
        return id;
    }

    public void setId(AssociationTagRecetteKey id) {
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
        AssociationTagRecette other = (AssociationTagRecette) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
