/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import jpa.entities.Image;
import jpa.entities.Ingredient;
import jpa.entities.Recipe;
import jpa.entities.Recipesteps;
import jpa.session.RecipeFacade;
import jpa.session.IngredientFacade;
import jpa.session.RecipestepsFacade;
import jpa.session.ImageFacade;

/**
 *
 * @author Greg
 */
@Named(value = "createRecipeBean")
@ViewScoped
public class CreateRecipeBean {
    
    private Recipe recipe;
    private ArrayList<Ingredient> ingredients = new ArrayList();
    private ArrayList<Recipesteps> steps = new ArrayList();
    private ArrayList<Image> images = new ArrayList();

    /**
     * Creates a new instance of CreateRecipeBean
     */
    @PostConstruct
    public void init() {
        
    }
    
    public String createRecipe() {
        return "index";
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

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
    
    public void addIngredient() {
        this.ingredients.add(new Ingredient());
    }
    
    public void start() {
        
    }
    
    
}
