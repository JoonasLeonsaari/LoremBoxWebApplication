package Model;

import Model.Files;
import Model.Folder;
import Model.Team;
import Model.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-11T16:19:16")
@StaticMetamodel(Folder.class)
public class Folder_ { 

    public static volatile SingularAttribute<Folder, Folder> parent;
    public static volatile CollectionAttribute<Folder, Files> filesCollection;
    public static volatile SingularAttribute<Folder, Date> dateCreated;
    public static volatile SingularAttribute<Folder, Team> teamId;
    public static volatile CollectionAttribute<Folder, Folder> folderCollection;
    public static volatile SingularAttribute<Folder, String> folderName;
    public static volatile SingularAttribute<Folder, Users> userId;
    public static volatile SingularAttribute<Folder, Integer> folderId;

}