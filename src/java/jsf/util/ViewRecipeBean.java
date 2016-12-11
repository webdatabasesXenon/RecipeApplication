/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
    
    Recipe recipe = new Recipe();
    ArrayList<Ingredient> ingredients = new ArrayList();
    ArrayList<Recipesteps> steps = new ArrayList();
    
    
    private DataModel items = null;
    @EJB
    private jpa.session.RecipeFacade ejbRecipeFacade;
    @EJB
    private jpa.session.IngredientFacade ejbIngredientFacade;
    @EJB
    private jpa.session.RecipestepsFacade ejbRecipestepsFacade;
    
    

    /**
     * Creates a new instance of ViewRecipeBean
     */
    public ViewRecipeBean() {
        
    }
    
    public void getDetails(Integer recipeid) {
        
        ingredients.clear();
        steps.clear();
        
        List<Recipe> recipeResults = ejbRecipeFacade.getEntityManager().createNamedQuery("Recipe.findByRecipeid").setParameter("recipeid", recipeid).getResultList();
        this.recipe = recipeResults.get(0);
        
        List<Ingredient> ingredientResults = this.ejbIngredientFacade.getEntityManager().createNamedQuery("Ingredient.findByReccipeid").setParameter("recipeid", recipeid).getResultList();
        
        ArrayList<Ingredient> tempIngredients = new ArrayList();               
            for (Ingredient ing: ingredientResults) {
                this.ingredients.add(ing);
            }
            
        List<Recipesteps> stepResults = this.ejbRecipestepsFacade.getEntityManager().createNamedQuery("Recipesteps.findByReccipeid").setParameter("recipeid", recipeid).getResultList();
        
        ArrayList<Recipesteps> tempSteps = new ArrayList();               
            for (Recipesteps rs: stepResults) {
                this.steps.add(rs);
            }
            
        System.out.println("The first function was called!!!!!!");

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Recipesteps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Recipesteps> steps) {
        this.steps = steps;
    }
    
    public String getRecipeName() {
        System.out.println("It's THIS!!!! " + recipe.getRecipename());
        return recipe.getRecipename();
    }
    
}
