package jsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jpa.entities.Image;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.session.ImageFacade;

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
import javax.servlet.http.HttpSession;
import jpa.entities.ImagePK;
import org.primefaces.event.FileUploadEvent;

@Named("imageController")
@SessionScoped
public class ImageController implements Serializable {

    private Image current;
    private DataModel items = null;
    @EJB
    private jpa.session.ImageFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ImageController() {
    }

    public Image getSelected() {
        if (current == null) {
            current = new Image();
            current.setImagePK(new jpa.entities.ImagePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ImageFacade getFacade() {
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
    public String getFirstPath(Integer recipeID){
        try{
        List <Image> results= this.ejbFacade.getEntityManager().createNamedQuery("Image.findByRecipeid").setParameter("recipeid", recipeID).getResultList();
        System.out.println("jsf.ImageController.getFirstPath() "+results.get(0).getPath().toString());
        if(results!=null)
            return results.get(0).getPath();
        else
            return "";
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
        
    }
    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Image) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Image();
        current.setImagePK(new jpa.entities.ImagePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            Integer recipeid = (Integer) session.getAttribute("CURRENT_RECIPE");
            ImagePK temp= new ImagePK(1,recipeid);
//            current.getImagePK().setImageno(1);
//            current.getImagePK().setRecipeid(recipeid);
            current= new Image(temp);
            System.out.println("jsf.ImageController.create()\n"/*+ this.getSelected().getPath()*/);
            //int location =this.getSelected().getPath().toString().lastIndexOf(".");
            //String temp2= this.getSelected().getPath().toString().substring(location);
            current.setPath("1-"+recipeid.toString()+".jpeg");
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            e.printStackTrace();
            return null;
        }
       
    }
    public String create(String ending) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            Integer recipeid = (Integer) session.getAttribute("CURRENT_RECIPE");
            ImagePK temp= new ImagePK(1,recipeid);
            current= new Image(temp);
            System.out.println("jsf.ImageController.create()\n"/*+ this.getSelected().getPath()*/);
            
            current.setPath("1-"+recipeid.toString()+ending);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            e.printStackTrace();
            return null;
        }
    }

    public String prepareEdit() {
        current = (Image) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getImagePK().setRecipeid(current.getRecipe().getRecipeid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Image) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageDeleted"));
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
    public void fileUpload(FileUploadEvent event)throws IOException{
        String path="C:\\Users\\Greg\\Desktop\\UNH\\Fall2016\\Recipe-Master-Safety\\RecipeApplication\\web\\resources\\Pictures\\";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String name = event.getFile().getFileName();
        int location =name.lastIndexOf(".");
        String temp2= name.substring(location);
        File file = new File(path+"1-"+session.getAttribute("CURRENT_RECIPE")+temp2);
        this.create(temp2);
        System.out.println("jsf.ImageController.fileUpload()\npath :"+path+"\nname: "+name);
        InputStream is =event.getFile().getInputstream();
        OutputStream os = new FileOutputStream(file);
        
        byte buffer[]=new byte[1024];
        int len;
        
        while((len=is.read(buffer))>0)
            os.write(buffer, 0, len);
        is.close();
        os.close();
        
        
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

    public Image getImage(jpa.entities.ImagePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Image.class)
    public static class ImageControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImageController controller = (ImageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imageController");
            return controller.getImage(getKey(value));
        }

        jpa.entities.ImagePK getKey(String value) {
            jpa.entities.ImagePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.ImagePK();
            key.setImageno(Integer.parseInt(values[0]));
            key.setRecipeid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.ImagePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getImageno());
            sb.append(SEPARATOR);
            sb.append(value.getRecipeid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Image) {
                Image o = (Image) object;
                return getStringKey(o.getImagePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Image.class.getName());
            }
        }

    }

}
