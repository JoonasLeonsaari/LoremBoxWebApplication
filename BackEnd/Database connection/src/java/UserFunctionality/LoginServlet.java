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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class runs a query with the user inputting USERNAME and PASSWORD.
 * 
 * It starts with requesting parameters from html form. After that it creates a
 * connection to the database. Then it runs a query and if it goes through,
 * a cookie is created with username as value and home.html is accessible by the user.
 * 
 * @author oskar
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){

        String username = request.getParameter("t1");
        String password = request.getParameter("t2");
        
        MyDb db = new MyDb();
        Connection con = db.getCon();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM lorembox.users WHERE username=? AND passwrd=?");
        
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        
        Cookie ck = new Cookie("auth", username);
        ck.setMaxAge(600);
        
        if (rs.next()){
            response.addCookie(ck);
            response.sendRedirect("home.html");
            
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('Wrong username or password')");
            out.println("window.location.href = 'index.html';");
            out.println("</script>");
        }
        
        } catch (SQLException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

