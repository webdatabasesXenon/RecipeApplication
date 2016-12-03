package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Recipe;
import jpa.entities.RecipestepsPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-03T14:24:02")
@StaticMetamodel(Recipesteps.class)
public class Recipesteps_ { 

    public static volatile SingularAttribute<Recipesteps, Integer> number;
    public static volatile SingularAttribute<Recipesteps, Recipe> recipe;
    public static volatile SingularAttribute<Recipesteps, String> text;
    public static volatile SingularAttribute<Recipesteps, RecipestepsPK> recipestepsPK;

}