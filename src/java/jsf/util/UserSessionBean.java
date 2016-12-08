/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jpa.entities.Friend;
import jpa.entities.Recipe;
import jpa.entities.User;
import jpa.session.UserFacade;
import jsf.UserController;


/**
 *
 * @author Greg
 */
@Named(value = "userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable {
    
    private User user = null;
    private boolean loggedIn = false;
    private ArrayList<Recipe> favoriteRecipes = new ArrayList();
    private ArrayList<User> connectedUsers = new ArrayList();
    private ArrayList<Recipe> createdRecipes = new ArrayList();
    
    private DataModel items = null;
    @EJB
    private jpa.session.UserFacade ejbFacade;
    @EJB
    private jpa.session.FriendFacade ejbFriendFacade;
    @EJB
    private jpa.session.RecipeFacade ejbRecipeFacade;
    
    private static Logger log = Logger.getLogger(UserSessionBean.class.getName());

    /**
     * Creates a new instance of UserSessionBean
     */
    public UserSessionBean() {
        //getUserSessionDetails();
        checkState();
    }
    
    private UserFacade getFacade() {
        return ejbFacade;
    }
    
    public void getUserSessionDetails() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        String username = null;
        
        if(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
            username = (String) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
        }

        // Debugging statement to console
        System.out.println(username);
        
        User usertemp = this.user;
        
        System.out.println(usertemp.getZipcode());
    }
    
    public void checkState() {
        
        if(this.user == null) {
            String username = null;
            if(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
                username = (String) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();

                List<User> results = this.ejbFacade.getEntityManager().createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
                User singleResult = results.get(0);
                
                this.user = singleResult;
                this.loggedIn = true;
                
                List<Friend> friendResults = this.ejbFriendFacade.getEntityManager().createNamedQuery("Friend.findByUserid1").setParameter("userid1", this.user.getUserid()).getResultList();
                ArrayList<Friend> friends = new ArrayList();               
                for (Friend f: friendResults) {
                    System.out.println("What is happening?");
                    System.out.println(f.toString());
                    friends.add(f);
                }
                
                
                for (Friend f: friends) {
                    List<User> connected = this.ejbFacade.getEntityManager().createNamedQuery("User.findByUserid").setParameter("userid", f.getUser1().getUserid()).getResultList();
                    User singleConnection = connected.get(0);
                    connectedUsers.add(singleConnection);
                }
                
                
                List<Recipe> createdRecipeResults = this.ejbRecipeFacade.getEntityManager().createNamedQuery("Recipe.findByUserid").setParameter("userid", this.user).getResultList(); 
                
                for (Recipe r: createdRecipeResults) {
                    createdRecipes.add(r);
                }
                
                List<Recipe> favoriteRecipeResults = this.ejbRecipeFacade.getEntityManager().createNamedQuery("Recipe.findFavorites").setParameter("user", this.user).getResultList(); 
                
                for (Recipe r: favoriteRecipeResults) {
                    favoriteRecipes.add(r);
                }

            }
        }
        
    }
    
    public String getUserID() {
        String id = null;
        if(this.user != null) {
            id = user.getUserid().toString();
        }
        return id;
    }
    
    public String getUsername() {
        String uname = null;
        if(this.user != null) {
            uname= user.getUsername();
        }
        return uname;    
        
    }  

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ArrayList<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFavoriteRecipes(ArrayList<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public ArrayList<User> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(ArrayList<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public ArrayList<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(ArrayList<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }
    
    public void addConnection(String username) {
        List<User> results = this.ejbFacade.getEntityManager().createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
        User connection = results.get(0);
        Friend friend = new Friend();
        friend.setUser(this.user);
        friend.setUser1(connection);
        Friend friend2 = new Friend();
        friend2.setUser(connection);
        friend2.setUser1(user);
        ejbFriendFacade.create(friend);
        ejbFriendFacade.create(friend2);
    }
    
    public void addFavorite(Integer recipeid) {
        List<Recipe> results = this.ejbRecipeFacade.getEntityManager().createNamedQuery("Recipe.findByRecipeid").setParameter("recipeid", recipeid).getResultList();
        Recipe favorite = results.get(0);
        Collection<User> userCollection;
        userCollection = favorite.getUserCollection();
        userCollection.add(this.user);
        favorite.setUserCollection(userCollection);
        ejbRecipeFacade.edit(favorite);
    }
    
    public String logout() {
        
        String destination = "/index?faces-redirect=true";

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
         
            this.user = null;
            this.loggedIn = false;
        }
        catch (Exception e) {
            destination = "/logouterror?faces-redirect=true";
        }
        
        
        return destination;
    }
    
    
    
}
