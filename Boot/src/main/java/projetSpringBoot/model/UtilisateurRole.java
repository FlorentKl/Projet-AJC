package projetSpringBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import projetSpringBoot.model.views.Views;

@Entity
@Table(name = "adherents_role")
@SequenceGenerator(name = "seqLoginRole", sequenceName = "seq_login_role", initialValue = 50, allocationSize = 1)
public class UtilisateurRole {
	@JsonView(Views.UtilisateurView.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLoginRole")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "username", foreignKey = @ForeignKey(name = "login_role_username_fk"))
	private Utilisateur utilisateur;

	@JsonView(Views.UtilisateurView.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 30)
	private Roles role;

	public UtilisateurRole() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
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
		UtilisateurRole other = (UtilisateurRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
