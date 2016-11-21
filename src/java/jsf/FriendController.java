package jsf;

import jpa.entities.Friend;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.FriendFacade;

import java.io.Serializable;
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

@Named("friendController")
@SessionScoped
public class FriendController implements Serializable {

    private Friend current;
    private DataModel items = null;
    @EJB
    private jpa.session.FriendFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public FriendController() {
    }

    public Friend getSelected() {
        if (current == null) {
            current = new Friend();
            current.setFriendPK(new jpa.entities.FriendPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private FriendFacade getFacade() {
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
        current = (Friend) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Friend();
        current.setFriendPK(new jpa.entities.FriendPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getFriendPK().setUserid1(current.getUser().getUserid());
            current.getFriendPK().setUserid2(current.getUser1().getUserid());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FriendCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Friend) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getFriendPK().setUserid1(current.getUser().getUserid());
            current.getFriendPK().setUserid2(current.getUser1().getUserid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FriendUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Friend) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FriendDeleted"));
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

    public Friend getFriend(jpa.entities.FriendPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Friend.class)
    public static class FriendControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FriendController controller = (FriendController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "friendController");
            return controller.getFriend(getKey(value));
        }

        jpa.entities.FriendPK getKey(String value) {
            jpa.entities.FriendPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.FriendPK();
            key.setUserid1(Integer.parseInt(values[0]));
            key.setUserid2(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.FriendPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUserid1());
            sb.append(SEPARATOR);
            sb.append(value.getUserid2());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Friend) {
                Friend o = (Friend) object;
                return getStringKey(o.getFriendPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Friend.class.getName());
            }
        }

    }

}
