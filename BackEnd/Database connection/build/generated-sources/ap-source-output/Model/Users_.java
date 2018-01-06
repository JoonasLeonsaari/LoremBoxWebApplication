package Model;

import Model.Files;
import Model.Folder;
import Model.Team;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-11T16:19:16")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Files> filesCollection;
    public static volatile SingularAttribute<Users, String> passwrd;
    public static volatile SingularAttribute<Users, Date> dateCreated;
    public static volatile CollectionAttribute<Users, Team> teamCollection;
    public static volatile CollectionAttribute<Users, Folder> folderCollection;
    public static volatile CollectionAttribute<Users, Files> filesCollection1;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SingularAttribute<Users, String> username;

}