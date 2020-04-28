package formationJpa.entity.recette;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EtapeRecette {
	private String text;
	private String photo;
	@ManyToOne
	@JoinColumn(name = "id_recette", foreignKey = @ForeignKey(name = "recette_etapeRecette_etapeRecette_fk"))
	private Recette id_recette;
	
	//TODO trouver comment déclarer des photo en java
	
	public EtapeRecette() {
		
	}

	public EtapeRecette(String text, String photo) {
		this.text = text;
		this.photo = photo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Recette getId_recette() {
		return id_recette;
	}

	public void setId_recette(Recette id_recette) {
		this.id_recette = id_recette;
	}
	
	

}
