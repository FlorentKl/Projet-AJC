package projetSpringBoot.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import projetSpringBoot.model.imageModel.ImageModel;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "adherents")
@SequenceGenerator(name = "seqUser", sequenceName = "seq_user", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @JsonView(value = { Views.UtilisateurView.class, Views.RecetteView.class, Views.RecetteWithAll.class,
            Views.TagView.class, Views.CommentaireView.class, Views.CommentaireListing.class })
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    @Column(name = "id_adherent")
    private Integer id;

    @JsonView(value = { Views.UtilisateurView.class, Views.RecetteView.class, Views.RecetteWithAll.class,
            Views.TagView.class, Views.CommentaireView.class, Views.CommentaireListing.class })
    @Column(name = "username", length = 150, nullable = false)
    private String pseudo;

    @JsonView(value = { Views.UtilisateurView.class })
    @Column(name = "password", length = 150, nullable = false)
    private String password;

    @JsonView(value = { Views.UtilisateurView.class, Views.RecetteView.class, Views.RecetteWithAll.class,
            Views.TagView.class, Views.CommentaireView.class, Views.CommentaireListing.class })
    @Column(name = "date_inscription")
    @Temporal(TemporalType.DATE)
    private Date dateInscription;

    @JsonView(value = { Views.UtilisateurView.class, Views.RecetteView.class, Views.RecetteWithAll.class,
            Views.TagView.class, Views.CommentaireView.class, Views.CommentaireListing.class })
    @Column(name = "enable")
    private Boolean enabled;

    @JsonView(value = { Views.UtilisateurView.class, Views.RecetteView.class, Views.RecetteWithAll.class,
            Views.TagView.class, Views.CommentaireView.class, Views.CommentaireListing.class })
    @OneToOne
    @JoinColumn(name = "id_img", referencedColumnName = "id_pic", foreignKey = @ForeignKey(name = "users_pic_FK"))
    private ImageModel imageProfil;

    @JsonView(value = { Views.UtilisateurView.class })
    @OneToMany(mappedBy = "auteur")
    private Set<Recette> recette;

    @JsonView(value = { Views.UtilisateurView.class })
    @OneToMany(mappedBy = "id.auteur")
    private Set<Commentaire> commentaires;

    @JsonView(value = { Views.UtilisateurView.class })
    @OneToMany(mappedBy = "utilisateur")
    private Set<UtilisateurRole> roles;

    @JsonView(value = { Views.UtilisateurView.class, Views.RecetteView.class, Views.RecetteWithAll.class,
            Views.TagView.class, Views.CommentaireView.class, Views.CommentaireListing.class })
    @Version
    private Integer version;

    public Utilisateur() {

    }

    public Utilisateur(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public Utilisateur(Integer id, String pseudo, String password) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ImageModel getImgProfil() {
        return imageProfil;
    }

    public void setImgProfil(ImageModel imageProfil) {
        this.imageProfil = imageProfil;
    }

    public Set<Recette> getRecette() {
        return recette;
    }

    public void setRecette(Set<Recette> recette) {
        this.recette = recette;
    }

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Set<UtilisateurRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UtilisateurRole> roles) {
        this.roles = roles;
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
        Utilisateur other = (Utilisateur) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
