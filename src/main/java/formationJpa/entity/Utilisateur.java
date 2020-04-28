package formationJpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import formationJpa.entity.recette.Recette;

@Entity
@Table(name = "user")
@SequenceGenerator(name = "seqUser", sequenceName = "seq_user", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
	@Column(name="id_user")
	private Integer id;
	@Column(name = "username", length = 150,nullable = false )
	private String pseudo;
	@Column(name = "password", length = 150,nullable = false)
	private String password;
	@OneToMany(mappedBy ="auteur")
	private List<Recette> recette;
	@OneToMany(mappedBy ="id.auteur")
	private List<AssociationRecetteCommentaires> commentaires;
	
	public Utilisateur() {
		
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

	public List<Recette> getRecette() {
		return recette;
	}

	public void setRecette(List<Recette> recette) {
		this.recette = recette;
	}

	public List<AssociationRecetteCommentaires> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<AssociationRecetteCommentaires> commentaires) {
		this.commentaires = commentaires;
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