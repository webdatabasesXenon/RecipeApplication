package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.ImagePK;
import jpa.entities.Recipe;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-10T11:41:25")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, String> path;
    public static volatile SingularAttribute<Image, ImagePK> imagePK;
    public static volatile SingularAttribute<Image, Recipe> recipe;

}