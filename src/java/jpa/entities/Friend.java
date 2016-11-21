/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "friend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friend.findAll", query = "SELECT f FROM Friend f"),
    @NamedQuery(name = "Friend.findByUserid1", query = "SELECT f FROM Friend f WHERE f.friendPK.userid1 = :userid1"),
    @NamedQuery(name = "Friend.findByUserid2", query = "SELECT f FROM Friend f WHERE f.friendPK.userid2 = :userid2"),
    @NamedQuery(name = "Friend.findByDate", query = "SELECT f FROM Friend f WHERE f.date = :date")})
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendPK friendPK;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "userid1", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "userid2", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Friend() {
    }

    public Friend(FriendPK friendPK) {
        this.friendPK = friendPK;
    }

    public Friend(int userid1, int userid2) {
        this.friendPK = new FriendPK(userid1, userid2);
    }

    public FriendPK getFriendPK() {
        return friendPK;
    }

    public void setFriendPK(FriendPK friendPK) {
        this.friendPK = friendPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendPK != null ? friendPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friend)) {
            return false;
        }
        Friend other = (Friend) object;
        if ((this.friendPK == null && other.friendPK != null) || (this.friendPK != null && !this.friendPK.equals(other.friendPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Friend[ friendPK=" + friendPK + " ]";
    }
    
}
