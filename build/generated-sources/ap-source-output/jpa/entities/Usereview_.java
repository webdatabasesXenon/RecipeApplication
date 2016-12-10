package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Recipe;
import jpa.entities.User;
import jpa.entities.UsereviewPK;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-08T19:39:05")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-10T11:33:47")
>>>>>>> greg-branch
@StaticMetamodel(Usereview.class)
public class Usereview_ { 

    public static volatile SingularAttribute<Usereview, String> comments;
    public static volatile SingularAttribute<Usereview, Integer> rating;
    public static volatile SingularAttribute<Usereview, Recipe> recipe;
    public static volatile SingularAttribute<Usereview, UsereviewPK> usereviewPK;
    public static volatile SingularAttribute<Usereview, User> user;

}