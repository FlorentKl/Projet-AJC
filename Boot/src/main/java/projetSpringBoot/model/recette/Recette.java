package projetSpringBoot.model.recette;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import projetSpringBoot.model.AssociationRecetteCommentaire;
import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.Ingredients.AssociationIngredientRecette;
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
    @JsonView(Views.Common.class)
    @Column(name = "name_recipe", length = 150)
    private String nom;

    @JsonView(Views.Common.class)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRecipe")
    private Integer id;

    @JsonView(Views.Common.class)
    @Column(name = "nbperson_recipe", length = 150)
    private Integer nbPersonne;

    @JsonView(Views.Common.class)
    @Column(name = "duration_recipe", length = 150)
    private Integer temps;

    @JsonView(Views.Common.class)
    @Column(name = "cost", length = 2)
    @Enumerated(EnumType.STRING)
    private Couts cout;

    @JsonView(Views.Common.class)
    @Column(name = "difficulte", length = 2)
    @Enumerated(EnumType.STRING)
    private Difficulte difficulte;

    @JsonView(Views.Common.class)
    @Lob
    @Column(name = "recipe_image")
    private byte[] photo;

    @OneToMany(mappedBy = "id.recette", cascade = CascadeType.REMOVE)
    private List<AssociationTagRecette> tags;

    @OneToMany(mappedBy = "id.recette", cascade = CascadeType.REMOVE)
    private List<AssociationIngredientRecette> ingredients;

    @OneToMany(mappedBy = "id_recette", cascade = CascadeType.REMOVE)
    private List<EtapeRecette> etapes;

    @OneToMany(mappedBy = "id.recette", cascade = CascadeType.REMOVE)
    private List<AssociationRecetteCommentaire> commentaires;

    @JsonView(Views.Common.class)
    @ManyToOne
    @JoinColumn(name = "auteur", foreignKey = @ForeignKey(name = "recette_auteur_fk"))
    private Utilisateur auteur;

    @JsonView(Views.Common.class)
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<AssociationTagRecette> getTags() {
        return tags;
    }

    public void setTags(List<AssociationTagRecette> tags) {
        this.tags = tags;
    }

    public List<AssociationIngredientRecette> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<AssociationIngredientRecette> ingredients) {
        this.ingredients = ingredients;
    }

    public List<EtapeRecette> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<EtapeRecette> etapes) {
        this.etapes = etapes;
    }

    public List<AssociationRecetteCommentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<AssociationRecetteCommentaire> commentaires) {
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
