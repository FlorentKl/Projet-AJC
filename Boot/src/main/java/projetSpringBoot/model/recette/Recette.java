package projetSpringBoot.model.recette;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.AssociationIngredientRecette;
import projetSpringBoot.model.imageModel.ImageModel;
import projetSpringBoot.model.tag.AssociationTagRecette;
import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "Recipe")
@SequenceGenerator(name = "seqRecipe", sequenceName = "seq_recipe", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 1)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Entree.class), @Type(value = Plat.class), @Type(value = Dessert.class),
        @Type(value = Boisson.class) })
public abstract class Recette {

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Column(name = "name_recipe", length = 150)
    private String nom;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRecipe")
    private Integer id;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Column(name = "nbperson_recipe", length = 150)
    private Integer nbPersonne;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Column(name = "duration_recipe", length = 150)
    private Integer temps;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Column(name = "cost", length = 2)
    @Enumerated(EnumType.STRING)
    private Couts cout;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Column(name = "difficulte", length = 2)
    @Enumerated(EnumType.STRING)
    private Difficulte difficulte;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @OneToOne
    @JoinColumn(name = "id_img", referencedColumnName = "id_pic", foreignKey = @ForeignKey(name = "recipe_pic_FK"))
    private ImageModel picture;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @JsonView(value = { Views.RecetteWithAll.class })
    @OneToMany(mappedBy = "id.recette", cascade = CascadeType.REMOVE)
    private Set<AssociationTagRecette> tags;

    @JsonView(value = { Views.RecetteWithAll.class })
    @OneToMany(mappedBy = "id.recette", cascade = CascadeType.REMOVE)
    private Set<AssociationIngredientRecette> ingredients;

    @JsonView(value = { Views.RecetteWithAll.class })
    @OneToMany(mappedBy = "id_recette", cascade = CascadeType.REMOVE)
    private Set<EtapeRecette> etapes;

    @JsonView(value = { Views.RecetteWithAll.class })
    @OneToMany(mappedBy = "id.recette", cascade = CascadeType.REMOVE)
    private Set<Commentaire> commentaires;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class })
    @ManyToOne
    @JoinColumn(name = "auteur", foreignKey = @ForeignKey(name = "recette_auteur_fk"))
    private Utilisateur auteur;

    @JsonView(value = { Views.RecetteView.class, Views.RecetteWithAll.class, Views.TagView.class,
            Views.IngredientView.class, Views.CommentaireView.class, Views.UtilisateurView.class })
    @Version
    private Integer version;

    public Recette() {

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

    public ImageModel getImgRecette() {
        return picture;
    }

    public void setImgRecette(ImageModel picture) {
        this.picture = picture;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<AssociationTagRecette> getTags() {
        return tags;
    }

    public void setTags(Set<AssociationTagRecette> tags) {
        this.tags = tags;
    }

    public Set<AssociationIngredientRecette> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<AssociationIngredientRecette> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<EtapeRecette> getEtapes() {
        return etapes;
    }

    public void setEtapes(Set<EtapeRecette> etapes) {
        this.etapes = etapes;
    }

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
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
        Recette other = (Recette) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
