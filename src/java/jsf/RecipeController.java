package jsf;
 
import jpa.entities.Recipe;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.RecipeFacade;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import jpa.entities.User;
 
@Named("recipeController")
@SessionScoped
public class RecipeController implements Serializable {
   
    private Recipe current;
    @Inject
    UserController daBean;
    private DataModel items = null;
    @EJB
    private jpa.session.RecipeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Integer theId=-1;
    private String searchString=null;
 
    public String getSearchString() {
        System.out.println("jsf.RecipeController.getSearchString() " + searchString);
        return searchString;
    }
 
    public void setSearchString(String searchString) {
        this.searchString = searchString;
        System.out.println("jsf.RecipeController.getSearchString() " + searchString);
    }
 
    public Integer getTheId() {
        System.out.println("get : theId "+theId);
        return theId;
    }
 
    public void setTheId(Integer theId) {
        System.out.println("set : theId "+theId);
        this.theId = theId;
    }
    public String bringMeHere(){
       FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      theId =  Integer.parseInt(params.get("theId"));
      return "/viewRecipe";
    }
    public RecipeController() {
    }
 
    public Recipe getSelected() {
        if (current == null) {
            current = new Recipe();
            selectedItemIndex = -1;
        }
        return current;
    }
    public String test2(){
        return "/index";
    }
    public List<Recipe> test(){
        String search;
        List<Recipe> results;
        System.out.println("Im clicked");
      
        if(searchString!=null&& !searchString.isEmpty()){
            results=getRecipeBySearch(searchString);
            searchString=null;
            return results;
           
        }
        results= ejbFacade.findAll();
        System.out.println("jsf.RecipeController.test() "+"Im here in test and search was null so i find it all");
        for(Recipe r:results)
            System.out.println(r.getRecipeid()+" "+r.getDescription());
       
        searchString=null;
        return results;
    }
 
    private RecipeFacade getFacade() {
        return ejbFacade;
    }
 
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
 
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }
 
                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
 
    public String prepareList() {
        recreateModel();
        return "List";
    }
 
    public String prepareView() {
        current = (Recipe) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
 
    public String prepareCreate() {
        current = new Recipe();
        selectedItemIndex = -1;
        return "create2";
    }
 
    public String create() {
       
        
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();  //added
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);  //added
            Integer userid = (Integer) session.getAttribute("USER_ID");  //added
            current.setUserid(daBean.getUser(userid));
           
            getFacade().create(current);  
            session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("CURRENT_RECIPE", current.getRecipeid());
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecipeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
 
    public String prepareEdit() {
        current = (Recipe) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
 
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecipeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
 
    public String destroy() {
        current = (Recipe) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
   
    public List<Recipe> getRecipeBySearch(String search){
       
        String temp="";
        String[] keywords = search.split(" ");
       
        List<Recipe> total=this.ejbFacade.findAll();
        ArrayList<Recipe> results= new ArrayList<Recipe>();
        for(Recipe r: total){
            temp=r.getDescription().toLowerCase();
            for(String s:keywords){
                if(s.length()<4)continue;
                s=s.toLowerCase();
                if(temp.contains(s))
                    if(!results.contains(r))
                        results.add(r);
            }
            temp="";
        }
        System.out.println("List of stuff");
        for (Recipe r:results)
            System.out.println(r.getRecipeid()+" "+r.getDescription());
        
        return results;
    }
    
    public String saveRecipe(Integer recipeid, User user) {
        try {
            List<Recipe> results = ejbFacade.getEntityManager().createNamedQuery("Recipe.findByRecipeid").setParameter("recipeid", recipeid).getResultList();
            
            current = results.get(0);
            
            current.getUserCollection().add(user);
            ejbFacade.edit(current);
            JsfUtil.addSuccessMessage("The recipe was added to your favorites");
        }
        catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addSuccessMessage("There was an error; the recipe was not added to your favorites");
        }
        return null;
    }
 
    public boolean checkFavorite(Integer recipeid, User user) {
        try {   
            List<Recipe> results = ejbFacade.getEntityManager().createNamedQuery("Recipe.findFavorites").setParameter("user", user).getResultList();
            
            for(Recipe r: results) {
                if(r.getRecipeid() == recipeid) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    
    public boolean checkAuthor(Integer recipeid, User user) {
        try {   
            List<Recipe> results = ejbFacade.getEntityManager().createNamedQuery("Recipe.findByRecipeid").setParameter("recipeid", recipeid).getResultList();
            
            Recipe recipe = results.get(0);
            
            if(recipe.getUserid().getUserid() == user.getUserid()) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
 
    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
 
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecipeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
 
    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }
 
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
 
    private void recreateModel() {
        items = null;
    }
 
    private void recreatePagination() {
        pagination = null;
    }
 
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
 
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
 
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }
 
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
 
    public Recipe getRecipe(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    public List<Recipe> getItemList(){
        return ejbFacade.findAll();
    }
 
    @FacesConverter(forClass = Recipe.class)
    public static class RecipeControllerConverter implements Converter {
 
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecipeController controller = (RecipeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recipeController");
            return controller.getRecipe(getKey(value));
        }
 
        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }
 
        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
 
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Recipe) {
                Recipe o = (Recipe) object;
                return getStringKey(o.getRecipeid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Recipe.class.getName());
            }
        }
 
    }
 
}
