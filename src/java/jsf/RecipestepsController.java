package jsf;

import jpa.entities.Recipesteps;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.RecipestepsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
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

@Named("recipestepsController")
@SessionScoped
public class RecipestepsController implements Serializable {
   
    private Recipesteps current;
    private DataModel items = null;
    @EJB
    private jpa.session.RecipestepsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    public RecipestepsController() {
    }

    public Recipesteps getSelected() {
        if (current == null) {
            current = new Recipesteps();
            current.setRecipestepsPK(new jpa.entities.RecipestepsPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private RecipestepsFacade getFacade() {
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
    
    public List<Recipesteps> getRecipeStepsById(Integer ID){
        List <Recipesteps> results= this.ejbFacade.getEntityManager().createNamedQuery("Recipesteps.findByRecipeid").setParameter("recipeid", ID).getResultList();
        return results;
        
        
    }

    public String prepareView() {
        current = (Recipesteps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Recipesteps();
        current.setRecipestepsPK(new jpa.entities.RecipestepsPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getRecipestepsPK().setRecipeid(current.getRecipe().getRecipeid());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecipestepsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Recipesteps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getRecipestepsPK().setRecipeid(current.getRecipe().getRecipeid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecipestepsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Recipesteps) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecipestepsDeleted"));
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

    public Recipesteps getRecipesteps(jpa.entities.RecipestepsPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Recipesteps.class)
    public static class RecipestepsControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecipestepsController controller = (RecipestepsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recipestepsController");
            return controller.getRecipesteps(getKey(value));
        }

        jpa.entities.RecipestepsPK getKey(String value) {
            jpa.entities.RecipestepsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.RecipestepsPK();
            key.setStepid(Integer.parseInt(values[0]));
            key.setRecipeid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.RecipestepsPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getStepid());
            sb.append(SEPARATOR);
            sb.append(value.getRecipeid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Recipesteps) {
                Recipesteps o = (Recipesteps) object;
                return getStringKey(o.getRecipestepsPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Recipesteps.class.getName());
            }
        }

    }

}
