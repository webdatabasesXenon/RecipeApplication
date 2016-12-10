package jpa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.FriendPK;
import jpa.entities.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-10T13:43:16")
@StaticMetamodel(Friend.class)
public class Friend_ { 

    public static volatile SingularAttribute<Friend, Date> date;
    public static volatile SingularAttribute<Friend, User> user1;
    public static volatile SingularAttribute<Friend, FriendPK> friendPK;
    public static volatile SingularAttribute<Friend, User> user;

}