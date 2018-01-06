/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserFunctionality;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is called when a file needs to be removed from the website.
 * Javascript gives this servlet the name of the file that needs to be deleted.
 * It runs a query that simply deletes information of the file from the database.
 * The file still remains on the server but all connection to it is broken.
 * 
 * @author oskar
 */

public class DeleteFile extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){

        String input = request.getParameter("delete");
        
        String filename = input.toString();
        
        MyDb db = new MyDb();
        Connection con = db.getCon();
        
        PreparedStatement pst = con.prepareStatement("DELETE FROM lorembox.files WHERE file_name=?");
        
        pst.setString(1, filename);

        
        out.println("<script type='text/javascript'>console.log('"+filename+"')</script>");
        
        pst.executeUpdate();

       
            out.println("<script type='text/javascript'>");
            out.println("alert('File deleted succesfully')");
            out.println("window.location.href = 'home.html';");
            out.println("</script>");
        
        } catch (SQLException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

