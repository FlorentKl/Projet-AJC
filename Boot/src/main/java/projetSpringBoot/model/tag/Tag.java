package projetSpringBoot.model.tag;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "tag")
@SequenceGenerator(name = "seqTag", sequenceName = "seq_tag", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Tag {
    @JsonView(value = { Views.RecetteWithAll.class, Views.TagView.class })
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTag")
    @Column(name = "id_tag")
    private Integer id;

    @JsonView(value = { Views.RecetteWithAll.class, Views.TagView.class })
    @Column(name = "tag", length = 150)
    private String tag;

    @JsonView(value = { Views.TagView.class })
    @OneToMany(mappedBy = "id.tag")
    private Set<AssociationTagRecette> recettes;

    @JsonView(value = { Views.RecetteWithAll.class, Views.TagView.class })
    @Version
    private Integer version;

    public Tag() {

    }

    public Tag(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<AssociationTagRecette> getRecettes() {
        return recettes;
    }

    public void setRecettes(Set<AssociationTagRecette> recettes) {
        this.recettes = recettes;
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
        Tag other = (Tag) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
