/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByImageno", query = "SELECT i FROM Image i WHERE i.imagePK.imageno = :imageno"),
    @NamedQuery(name = "Image.findByRecipeid", query = "SELECT i FROM Image i WHERE i.imagePK.recipeid = :recipeid"),
    @NamedQuery(name = "Image.findByPath", query = "SELECT i FROM Image i WHERE i.path = :path")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImagePK imagePK;
    @Size(max = 255)
    @Column(name = "path")
    private String path;
    @JoinColumn(name = "recipeid", referencedColumnName = "recipeid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;

    public Image() {
    }

    public Image(ImagePK imagePK) {
        this.imagePK = imagePK;
    }

    public Image(int imageno, int recipeid) {
        this.imagePK = new ImagePK(imageno, recipeid);
    }

    public ImagePK getImagePK() {
        return imagePK;
    }

    public void setImagePK(ImagePK imagePK) {
        this.imagePK = imagePK;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imagePK != null ? imagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imagePK == null && other.imagePK != null) || (this.imagePK != null && !this.imagePK.equals(other.imagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Image[ imagePK=" + imagePK + " ]";
    }
    
}
