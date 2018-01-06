
package Controller;
 
import Model.Files;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import Model.Users;
import UserFunctionality.MyDb;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

 
/**
 * REST Web Service
 */
@Path("service")
public class DBService {
   
    @EJB
    private DBControl dbc;
 
    /**
     * Creates a new instance of DBService
     */
    public DBService() {
    }

    //JSON for users
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Users> getJson() {
      
        return dbc.getAll();
    }
    //Prints JSON file depending on who is logged in through cookie
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("jsonboii")
    public List<Files> jaskason(@CookieParam("auth") String user) {
        
        return dbc.getMedia(user);
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteFile")
    public List<Files> deleteFile(String filename) {
        
        return dbc.getMedia(filename);
    }
    
    //new user register
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("setpass")
    public Users post(@FormParam("username") String name, @FormParam("password") String pass) {
        
        Users u = new Users();
        
       try {
            MyDb db = new MyDb();
            Connection con = db.getCon();
            java.sql.Statement stmt = con.createStatement();
 
            String SQL = "SELECT * FROM lorembox.users WHERE username='" + name + "'";
 
            ResultSet rs = stmt.executeQuery(SQL);
 
            if (rs.next()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Wrong username or password')");
            out.println("window.location.href = 'index.html';");
            out.println("</script>");
 
            } else {
                
                u.setUsername(name);
                u.setPasswrd(pass);
       
                return dbc.insert(u);
            }
 
        } catch (Exception e) {
 
            System.out.println("Error : " + e.getMessage());
        } 
      
       return null;
    }
    
       
    /**
     * PUT method for updating or creating an instance of DBService
     * @param content representation for the resource
     */
    //@PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    //public void putJson(String content) {
    //}
}
 

