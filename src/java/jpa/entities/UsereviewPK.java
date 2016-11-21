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
public class UsereviewPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipeid")
    private int recipeid;

    public UsereviewPK() {
    }

    public UsereviewPK(int userid, int recipeid) {
        this.userid = userid;
        this.recipeid = recipeid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
        hash += (int) userid;
        hash += (int) recipeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsereviewPK)) {
            return false;
        }
        UsereviewPK other = (UsereviewPK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.recipeid != other.recipeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.UsereviewPK[ userid=" + userid + ", recipeid=" + recipeid + " ]";
    }
    
}
