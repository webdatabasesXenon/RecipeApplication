/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bhanu
 */
@Embeddable
public class ImagePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "imageno")
    private int imageno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipeid")
    private int recipeid;

    public ImagePK() {
    }

    public ImagePK(int imageno, int recipeid) {
        this.imageno = imageno;
        this.recipeid = recipeid;
    }

    public int getImageno() {
        return imageno;
    }

    public void setImageno(int imageno) {
        this.imageno = imageno;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) imageno;
        hash += (int) recipeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagePK)) {
            return false;
        }
        ImagePK other = (ImagePK) object;
        if (this.imageno != other.imageno) {
            return false;
        }
        if (this.recipeid != other.recipeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ImagePK[ imageno=" + imageno + ", recipeid=" + recipeid + " ]";
    }
    
}
