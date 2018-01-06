package Model;

import Model.Folder;
import Model.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-11T16:19:16")
@StaticMetamodel(Team.class)
public class Team_ { 

    public static volatile SingularAttribute<Team, String> teamName;
    public static volatile SingularAttribute<Team, Date> dateCreated;
    public static volatile SingularAttribute<Team, Integer> teamId;
    public static volatile CollectionAttribute<Team, Folder> folderCollection;
    public static volatile CollectionAttribute<Team, Users> usersCollection;

}