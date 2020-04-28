package formationJpa.entity.recette;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_step")
@SequenceGenerator(name = "seqEtape", sequenceName = "seq_etape", initialValue = 100, allocationSize = 1)
public class EtapeRecette {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqEtape")
	@Column(name = "id_etape")
	private Integer id;
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
