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
@Table(name = "recipesteps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipesteps.findAll", query = "SELECT r FROM Recipesteps r"),
    @NamedQuery(name = "Recipesteps.findByStepid", query = "SELECT r FROM Recipesteps r WHERE r.recipestepsPK.stepid = :stepid"),
    @NamedQuery(name = "Recipesteps.findByRecipeid", query = "SELECT r FROM Recipesteps r WHERE r.recipestepsPK.recipeid = :recipeid"),
    @NamedQuery(name = "Recipesteps.findByNumber", query = "SELECT r FROM Recipesteps r WHERE r.number = :number"),
    @NamedQuery(name = "Recipesteps.findByText", query = "SELECT r FROM Recipesteps r WHERE r.text = :text")})
public class Recipesteps implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipestepsPK recipestepsPK;
    @Column(name = "number")
    private Integer number;
    @Size(max = 255)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "recipeid", referencedColumnName = "recipeid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;

    public Recipesteps() {
    }

    public Recipesteps(RecipestepsPK recipestepsPK) {
        this.recipestepsPK = recipestepsPK;
    }

    public Recipesteps(int stepid, int recipeid) {
        this.recipestepsPK = new RecipestepsPK(stepid, recipeid);
    }

    public RecipestepsPK getRecipestepsPK() {
        return recipestepsPK;
    }

    public void setRecipestepsPK(RecipestepsPK recipestepsPK) {
        this.recipestepsPK = recipestepsPK;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        hash += (recipestepsPK != null ? recipestepsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipesteps)) {
            return false;
        }
        Recipesteps other = (Recipesteps) object;
        if ((this.recipestepsPK == null && other.recipestepsPK != null) || (this.recipestepsPK != null && !this.recipestepsPK.equals(other.recipestepsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Recipesteps[ recipestepsPK=" + recipestepsPK + " ]";
    }
    
}
