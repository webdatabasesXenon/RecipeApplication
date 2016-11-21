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
public class RecipestepsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "stepid")
    private int stepid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipeid")
    private int recipeid;

    public RecipestepsPK() {
    }

    public RecipestepsPK(int stepid, int recipeid) {
        this.stepid = stepid;
        this.recipeid = recipeid;
    }

    public int getStepid() {
        return stepid;
    }

    public void setStepid(int stepid) {
        this.stepid = stepid;
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
        hash += (int) stepid;
        hash += (int) recipeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipestepsPK)) {
            return false;
        }
        RecipestepsPK other = (RecipestepsPK) object;
        if (this.stepid != other.stepid) {
            return false;
        }
        if (this.recipeid != other.recipeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.RecipestepsPK[ stepid=" + stepid + ", recipeid=" + recipeid + " ]";
    }
    
}
