/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Usereview;

/**
 *
 * @author bhanu
 */
@Stateless
public class UsereviewFacade extends AbstractFacade<Usereview> {

    @PersistenceContext(unitName = "RecipeApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsereviewFacade() {
        super(Usereview.class);
    }
    
}
