
package Controller;
 
import Model.Files;
import Model.Users;
import com.google.gson.Gson;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

 
/**
 *
 
 */
@Stateless
public class DBControl {
   
    @PersistenceContext
    private EntityManager em;
 
    public DBControl() {
    }
    //finds all users from the database
    public List<Users> getAll(){
       List<Users> lst = em.createNamedQuery("User.findAll").getResultList(); 
       return lst;
        //em.createNamedQuery("users.findAll").getResultList();
        //List<User> lst = em.createNamedQuery("User.findAll").getResultList(); return lst;
    }
    //finds a user by name
    public String getUser(Users u) {
    
        return em.createNamedQuery("users.findByName").toString();
    }
    //finds files for a specific user
    public List<Files> getMedia(String username){
        List<Files> lst = em.createNamedQuery("Files.findUserFiles").setParameter("username", username).getResultList();
        return lst;
    }
    
     //delete file for a specific user
    public List<Files> deleteMedia(String filename){
        List<Files> lst = em.createNamedQuery("Files..deleteUserFile").setParameter("fileName", filename).getResultList();
        return lst;
    }

    //inserts usernames into db
    public Users insert (Users u){
        em.persist(u);
        return u;
    }
   //inserts files into db
    public Files insertFiles (Files f){
        em.persist(f);
        return f;
    }
}
    
    
    

/*    void getAll(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}*/