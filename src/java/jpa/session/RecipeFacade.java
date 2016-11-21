/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Recipe;

/**
 *
 * @author bhanu
 */
@Stateless
public class RecipeFacade extends AbstractFacade<Recipe> {

    @PersistenceContext(unitName = "RecipeApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecipeFacade() {
        super(Recipe.class);
    }
    
}
