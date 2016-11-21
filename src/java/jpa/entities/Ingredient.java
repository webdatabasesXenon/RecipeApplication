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
@Table(name = "ingredient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findByIngredientid", query = "SELECT i FROM Ingredient i WHERE i.ingredientPK.ingredientid = :ingredientid"),
    @NamedQuery(name = "Ingredient.findByRecipeid", query = "SELECT i FROM Ingredient i WHERE i.ingredientPK.recipeid = :recipeid"),
    @NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE i.name = :name"),
    @NamedQuery(name = "Ingredient.findByUnit", query = "SELECT i FROM Ingredient i WHERE i.unit = :unit"),
    @NamedQuery(name = "Ingredient.findByQuantity", query = "SELECT i FROM Ingredient i WHERE i.quantity = :quantity")})
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IngredientPK ingredientPK;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "unit")
    private String unit;
    @Size(max = 255)
    @Column(name = "quantity")
    private String quantity;
    @JoinColumn(name = "recipeid", referencedColumnName = "recipeid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(IngredientPK ingredientPK) {
        this.ingredientPK = ingredientPK;
    }

    public Ingredient(int ingredientid, int recipeid) {
        this.ingredientPK = new IngredientPK(ingredientid, recipeid);
    }

    public IngredientPK getIngredientPK() {
        return ingredientPK;
    }

    public void setIngredientPK(IngredientPK ingredientPK) {
        this.ingredientPK = ingredientPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
        hash += (ingredientPK != null ? ingredientPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.ingredientPK == null && other.ingredientPK != null) || (this.ingredientPK != null && !this.ingredientPK.equals(other.ingredientPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Ingredient[ ingredientPK=" + ingredientPK + " ]";
    }
    
}
