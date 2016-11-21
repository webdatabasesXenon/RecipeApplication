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
public class IngredientPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ingredientid")
    private int ingredientid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipeid")
    private int recipeid;

    public IngredientPK() {
    }

    public IngredientPK(int ingredientid, int recipeid) {
        this.ingredientid = ingredientid;
        this.recipeid = recipeid;
    }

    public int getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(int ingredientid) {
        this.ingredientid = ingredientid;
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
        hash += (int) ingredientid;
        hash += (int) recipeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngredientPK)) {
            return false;
        }
        IngredientPK other = (IngredientPK) object;
        if (this.ingredientid != other.ingredientid) {
            return false;
        }
        if (this.recipeid != other.recipeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.IngredientPK[ ingredientid=" + ingredientid + ", recipeid=" + recipeid + " ]";
    }
    
}
