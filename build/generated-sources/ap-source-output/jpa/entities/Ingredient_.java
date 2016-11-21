package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.IngredientPK;
import jpa.entities.Recipe;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-21T17:52:11")
@StaticMetamodel(Ingredient.class)
public class Ingredient_ { 

    public static volatile SingularAttribute<Ingredient, IngredientPK> ingredientPK;
    public static volatile SingularAttribute<Ingredient, String> unit;
    public static volatile SingularAttribute<Ingredient, String> quantity;
    public static volatile SingularAttribute<Ingredient, String> name;
    public static volatile SingularAttribute<Ingredient, Recipe> recipe;

}