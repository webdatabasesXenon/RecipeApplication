package jpa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Friend;
import jpa.entities.Recipe;
import jpa.entities.Usereview;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T11:05:35")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> allergies;
    public static volatile SingularAttribute<User, Date> date;
    public static volatile SingularAttribute<User, String> country;
    public static volatile CollectionAttribute<User, Friend> friendCollection1;
    public static volatile CollectionAttribute<User, Usereview> usereviewCollection;
    public static volatile SingularAttribute<User, Integer> userid;
    public static volatile CollectionAttribute<User, Recipe> recipeCollection;
    public static volatile SingularAttribute<User, Integer> zipcode;
    public static volatile SingularAttribute<User, String> password;
    public static volatile CollectionAttribute<User, Friend> friendCollection;
    public static volatile CollectionAttribute<User, Recipe> recipeCollection1;
    public static volatile SingularAttribute<User, String> diet;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}