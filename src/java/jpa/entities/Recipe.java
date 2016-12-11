/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "recipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r"),
    @NamedQuery(name = "Recipe.findByRecipeid", query = "SELECT r FROM Recipe r WHERE r.recipeid = :recipeid"),
    @NamedQuery(name = "Recipe.findByRecipename", query = "SELECT r FROM Recipe r WHERE r.recipename = :recipename"),
    @NamedQuery(name = "Recipe.findByDescription", query = "SELECT r FROM Recipe r WHERE r.description = :description"),
    @NamedQuery(name = "Recipe.findByVisit", query = "SELECT r FROM Recipe r WHERE r.visit = :visit"),
    @NamedQuery(name = "Recipe.findByReview", query = "SELECT r FROM Recipe r WHERE r.review = :review"),
    @NamedQuery(name = "Recipe.findByAvgreview", query = "SELECT r FROM Recipe r WHERE r.avgreview = :avgreview"),
    @NamedQuery(name = "Recipe.findByDate", query = "SELECT r FROM Recipe r WHERE r.date = :date"),
    @NamedQuery(name = "Recipe.findFavorites", query = "SELECT r FROM Recipe r WHERE r.userCollection = :user"),
    @NamedQuery(name = "Recipe.findByUserid", query = "SELECT r FROM Recipe r WHERE r.userid = :userid")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recipeid")
    private Integer recipeid;
    @Size(max = 255)
    @Column(name = "recipename")
    private String recipename;
    @Size(max = 1000)
    @Column(name = "description")
    private String description;
    @Column(name = "visit")
    private Integer visit;
    @Column(name = "review")
    private Integer review;
    @Column(name = "avgreview")
    private Integer avgreview;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinTable(name = "favorite_recipe", joinColumns = {
        @JoinColumn(name = "recipeid", referencedColumnName = "recipeid")}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid")})
    @ManyToMany
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Collection<Image> imageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Collection<Ingredient> ingredientCollection;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private User userid;
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Collection<Recipesteps> recipestepsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Collection<Usereview> usereviewCollection;

    public Recipe() {
    }

    public Recipe(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public Integer getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public Integer getAvgreview() {
        return avgreview;
    }

    public void setAvgreview(Integer avgreview) {
        this.avgreview = avgreview;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<Ingredient> getIngredientCollection() {
        return ingredientCollection;
    }

    public void setIngredientCollection(Collection<Ingredient> ingredientCollection) {
        this.ingredientCollection = ingredientCollection;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Collection<Recipesteps> getRecipestepsCollection() {
        return recipestepsCollection;
    }

    public void setRecipestepsCollection(Collection<Recipesteps> recipestepsCollection) {
        this.recipestepsCollection = recipestepsCollection;
    }

    @XmlTransient
    public Collection<Usereview> getUsereviewCollection() {
        return usereviewCollection;
    }

    public void setUsereviewCollection(Collection<Usereview> usereviewCollection) {
        this.usereviewCollection = usereviewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeid != null ? recipeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.recipeid == null && other.recipeid != null) || (this.recipeid != null && !this.recipeid.equals(other.recipeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description + recipename;
    }
    
}
