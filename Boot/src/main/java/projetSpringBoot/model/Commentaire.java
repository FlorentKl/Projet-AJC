package projetSpringBoot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "comment")
public class Commentaire {
    @JsonView(value = { Views.RecetteWithAll.class, Views.CommentaireView.class })
    @Column(name = "text")
    private String texte;

    @JsonView(value = { Views.RecetteWithAll.class, Views.CommentaireView.class })
    @EmbeddedId
    private CommentaireKey id;

    @JsonView(value = { Views.RecetteWithAll.class, Views.CommentaireView.class })
    @Column(name = "notation")
    private Integer note;

    @JsonView(value = { Views.RecetteWithAll.class, Views.CommentaireView.class })
    @Column(name = "date_commentaire")
    @Temporal(TemporalType.DATE)
    private Date dateCommentaire;

    @JsonView(value = { Views.RecetteWithAll.class, Views.CommentaireView.class })
    @Version
    private Integer version;

    public Commentaire(String texte, CommentaireKey id, Integer note) {
        this.texte = texte;
        this.id = id;
        this.note = note;
    }

    public Commentaire() {

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

    public CommentaireKey getId() {
        return id;
    }

    public void setId(CommentaireKey id) {
        this.id = id;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
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
        Commentaire other = (Commentaire) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
