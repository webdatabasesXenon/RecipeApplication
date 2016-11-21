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
public class FriendPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "userid1")
    private int userid1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid2")
    private int userid2;

    public FriendPK() {
    }

    public FriendPK(int userid1, int userid2) {
        this.userid1 = userid1;
        this.userid2 = userid2;
    }

    public int getUserid1() {
        return userid1;
    }

    public void setUserid1(int userid1) {
        this.userid1 = userid1;
    }

    public int getUserid2() {
        return userid2;
    }

    public void setUserid2(int userid2) {
        this.userid2 = userid2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userid1;
        hash += (int) userid2;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendPK)) {
            return false;
        }
        FriendPK other = (FriendPK) object;
        if (this.userid1 != other.userid1) {
            return false;
        }
        if (this.userid2 != other.userid2) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.FriendPK[ userid1=" + userid1 + ", userid2=" + userid2 + " ]";
    }
    
}
