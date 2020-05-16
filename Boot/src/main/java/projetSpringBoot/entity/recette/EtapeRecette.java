package projetSpringBoot.entity.recette;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_step")
@SequenceGenerator(name = "seqEtape", sequenceName = "seq_etape", initialValue = 100, allocationSize = 1)
public class EtapeRecette {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEtape")
    @Column(name = "id_etape")
    private Integer id;
    private String text;
    @Lob
    @Column(name = "recipe_step_photo")
    private byte[] photo;
    @Column(name = "recipe_step_number")
    private Integer numEtape;
    @ManyToOne
    @JoinColumn(name = "id_recette", foreignKey = @ForeignKey(name = "recette_etapeRecette_etapeRecette_fk"))
    private Recette id_recette;

    // TODO trouver comment d�clarer des photo en java

    public EtapeRecette() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Recette getId_recette() {
        return id_recette;
    }

    public void setId_recette(Recette id_recette) {
        this.id_recette = id_recette;
    }

    public Integer getNumEtape() {
        return numEtape;
    }

    public void setNumEtape(Integer numEtape) {
        this.numEtape = numEtape;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((id_recette == null) ? 0 : id_recette.hashCode());
        result = prime * result + ((numEtape == null) ? 0 : numEtape.hashCode());
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
        EtapeRecette other = (EtapeRecette) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (id_recette == null) {
            if (other.id_recette != null)
                return false;
        } else if (!id_recette.equals(other.id_recette))
            return false;
        if (numEtape == null) {
            if (other.numEtape != null)
                return false;
        } else if (!numEtape.equals(other.numEtape))
            return false;
        return true;
    }

}