package jpa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Image;
import jpa.entities.Ingredient;
import jpa.entities.Recipesteps;
import jpa.entities.User;
import jpa.entities.Usereview;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-05T20:42:15")
@StaticMetamodel(Recipe.class)
public class Recipe_ { 

    public static volatile SingularAttribute<Recipe, Date> date;
    public static volatile SingularAttribute<Recipe, String> recipename;
    public static volatile SingularAttribute<Recipe, Integer> avgreview;
    public static volatile CollectionAttribute<Recipe, User> userCollection;
    public static volatile SingularAttribute<Recipe, String> description;
    public static volatile CollectionAttribute<Recipe, Usereview> usereviewCollection;
    public static volatile SingularAttribute<Recipe, User> userid;
    public static volatile SingularAttribute<Recipe, Integer> recipeid;
    public static volatile SingularAttribute<Recipe, Integer> review;
    public static volatile CollectionAttribute<Recipe, Image> imageCollection;
    public static volatile CollectionAttribute<Recipe, Ingredient> ingredientCollection;
    public static volatile SingularAttribute<Recipe, Integer> visit;
    public static volatile CollectionAttribute<Recipe, Recipesteps> recipestepsCollection;

}