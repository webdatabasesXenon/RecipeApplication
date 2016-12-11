package jsf;

import jpa.entities.Ingredient;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.IngredientFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

 import javax.servlet.http.HttpSession;
 import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import jpa.entities.IngredientPK;

@Named("ingredientController")
@SessionScoped
public class IngredientController implements Serializable {

    private Ingredient current;
    private DataModel items = null;
    @EJB
    private jpa.session.IngredientFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public IngredientController() {
    }

    public Ingredient getSelected() {
        if (current == null) {
            current = new Ingredient();
            current.setIngredientPK(new jpa.entities.IngredientPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private IngredientFacade getFacade() {
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
        current = (Ingredient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Ingredient();
        current.setIngredientPK(new jpa.entities.IngredientPK());
        selectedItemIndex = -1;
        return "create2";
    }

    public String create() {
        System.out.println("jsf.IngredientController.create() ");
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();  //added
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);  //added
            Integer recipeid = (Integer) session.getAttribute("CURRENT_RECIPE");  //added
            current.getIngredientPK().setRecipeid(recipeid);
            
            getFacade().create(current);
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IngredientCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            e.printStackTrace();
            return null;
        }
    }

    public String prepareEdit() {
        current = (Ingredient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getIngredientPK().setRecipeid(current.getRecipe().getRecipeid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IngredientUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public List<Ingredient> getByRecipeId(Integer ID){
        
        List <Ingredient> results= this.ejbFacade.getEntityManager().createNamedQuery("Ingredient.findByRecipeid").
                setParameter("recipeid", ID).getResultList();
            
        
        return results;
    }

    public String destroy() {
        current = (Ingredient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IngredientDeleted"));
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

    public Ingredient getIngredient(jpa.entities.IngredientPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Ingredient.class)
    public static class IngredientControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IngredientController controller = (IngredientController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ingredientController");
            return controller.getIngredient(getKey(value));
        }

        jpa.entities.IngredientPK getKey(String value) {
            jpa.entities.IngredientPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.IngredientPK();
            key.setIngredientid(Integer.parseInt(values[0]));
            key.setRecipeid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.IngredientPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIngredientid());
            sb.append(SEPARATOR);
            sb.append(value.getRecipeid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ingredient) {
                Ingredient o = (Ingredient) object;
                return getStringKey(o.getIngredientPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ingredient.class.getName());
            }
        }

    }

}
