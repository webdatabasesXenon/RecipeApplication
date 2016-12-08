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
import javax.persistence.ManyToMany;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
    @NamedQuery(name = "User.findByZipcode", query = "SELECT u FROM User u WHERE u.zipcode = :zipcode"),
    @NamedQuery(name = "User.findByDiet", query = "SELECT u FROM User u WHERE u.diet = :diet"),
    @NamedQuery(name = "User.findByAllergies", query = "SELECT u FROM User u WHERE u.allergies = :allergies"),
    @NamedQuery(name = "User.findByDate", query = "SELECT u FROM User u WHERE u.date = :date")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "country")
    private String country;
    @Column(name = "zipcode")
    private Integer zipcode;
    @Size(max = 255)
    @Column(name = "diet")
    private String diet;
    @Size(max = 255)
    @Column(name = "allergies")
    private String allergies;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Recipe> recipeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Friend> friendCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private Collection<Friend> friendCollection1;
    @OneToMany(mappedBy = "userid")
    private Collection<Recipe> recipeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Usereview> usereviewCollection;

    public User() {
    }

    public User(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<Recipe> getRecipeCollection() {
        return recipeCollection;
    }

    public void setRecipeCollection(Collection<Recipe> recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    @XmlTransient
    public Collection<Friend> getFriendCollection() {
        return friendCollection;
    }

    public void setFriendCollection(Collection<Friend> friendCollection) {
        this.friendCollection = friendCollection;
    }

    @XmlTransient
    public Collection<Friend> getFriendCollection1() {
        return friendCollection1;
    }

    public void setFriendCollection1(Collection<Friend> friendCollection1) {
        this.friendCollection1 = friendCollection1;
    }

    @XmlTransient
    public Collection<Recipe> getRecipeCollection1() {
        return recipeCollection1;
    }

    public void setRecipeCollection1(Collection<Recipe> recipeCollection1) {
        this.recipeCollection1 = recipeCollection1;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.User[ userid=" + userid + " ]";
    }
    
}
