package projetSpringBoot.model.imageModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "picture")
@SequenceGenerator(name = "seqImage", sequenceName = "seq_picture", initialValue = 100, allocationSize = 1)
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqImage")
    @Column(name = "id_pic")
    private Integer id;

    @Column(name = "pic_name")
    private String name;

    @Column(name = "pic_type")
    private String type;

    @Lob
    @Column(name = "pic")
    private byte[] image;

    public ImageModel() {
    }

    public ImageModel(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
        ImageModel other = (ImageModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}