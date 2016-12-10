/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import jpa.entities.Ingredient;
import jpa.entities.Recipe;
import jpa.entities.Recipesteps;

/**
 *
 * @author Greg
 */
@Named(value = "viewRecipeBean")
@SessionScoped
public class ViewRecipeBean implements Serializable {
    
    Recipe recipe;
    Ingredient ingredients;
    Recipesteps steps;
    

    /**
     * Creates a new instance of ViewRecipeBean
     */
    public ViewRecipeBean() {
    }
    
}
