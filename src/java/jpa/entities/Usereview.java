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
@Table(name = "usereview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usereview.findAll", query = "SELECT u FROM Usereview u"),
    @NamedQuery(name = "Usereview.findByUserid", query = "SELECT u FROM Usereview u WHERE u.usereviewPK.userid = :userid"),
    @NamedQuery(name = "Usereview.findByRecipeid", query = "SELECT u FROM Usereview u WHERE u.usereviewPK.recipeid = :recipeid"),
    @NamedQuery(name = "Usereview.findByComments", query = "SELECT u FROM Usereview u WHERE u.comments = :comments"),
    @NamedQuery(name = "Usereview.findByRating", query = "SELECT u FROM Usereview u WHERE u.rating = :rating")})
public class Usereview implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsereviewPK usereviewPK;
    @Size(max = 255)
    @Column(name = "comments")
    private String comments;
    @Column(name = "rating")
    private Integer rating;
    @JoinColumn(name = "recipeid", referencedColumnName = "recipeid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Usereview() {
    }

    public Usereview(UsereviewPK usereviewPK) {
        this.usereviewPK = usereviewPK;
    }

    public Usereview(int userid, int recipeid) {
        this.usereviewPK = new UsereviewPK(userid, recipeid);
    }

    public UsereviewPK getUsereviewPK() {
        return usereviewPK;
    }

    public void setUsereviewPK(UsereviewPK usereviewPK) {
        this.usereviewPK = usereviewPK;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usereviewPK != null ? usereviewPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usereview)) {
            return false;
        }
        Usereview other = (Usereview) object;
        if ((this.usereviewPK == null && other.usereviewPK != null) || (this.usereviewPK != null && !this.usereviewPK.equals(other.usereviewPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Usereview[ usereviewPK=" + usereviewPK + " ]";
    }
    
}
