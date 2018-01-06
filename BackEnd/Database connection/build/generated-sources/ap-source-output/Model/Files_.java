package Model;

import Model.Folder;
import Model.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-11T16:19:16")
@StaticMetamodel(Files.class)
public class Files_ { 

    public static volatile SingularAttribute<Files, String> fileName;
    public static volatile SingularAttribute<Files, Date> uploadDate;
    public static volatile SingularAttribute<Files, String> filePath;
    public static volatile CollectionAttribute<Files, Users> usersCollection;
    public static volatile SingularAttribute<Files, Users> userId;
    public static volatile SingularAttribute<Files, Folder> folderId;
    public static volatile SingularAttribute<Files, Integer> fileId;
    public static volatile SingularAttribute<Files, String> username;

}