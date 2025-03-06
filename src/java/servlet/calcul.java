/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.http.HttpSession;



/**
 *
 * @author keliane
 */
@WebServlet(name = "calcul", urlPatterns = {"/calcul"})  //very imporatnt for calcul to match w html form action
public class calcul extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            String operation = request.getParameter("operation");
            double resultat = 0;
            
            if (operation.equals("+")){
                resultat=num1+num2;   
            }
            else if (operation.equals("-")){
                resultat=num1-num2;
            }
            else if (operation.equals("*")){
                resultat=num1*num2;
            }
            else if (operation.equals("/")){
                if (num2!=0){
                resultat=num1/num2;
                }else {
                    out.println("Error : Division by zero impossible ");
                    return;
                }
            }
            
            HttpSession session = request.getSession();
            String lastAccess = (String) session.getAttribute("lastAccess");
            String currentAccess = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            session.setAttribute("lastAccess", currentAccess);
            
            out.println("<html><body>");
            out.println("<h2>Résultat : " + resultat + "</h2>");

             
             if (lastAccess != null) {
                 out.println("<p>Dernier accès : " + lastAccess + "</p>");
             } else {
                 out.println("<p> Hey, Bienvenue sur Calculatrice Servlet. </p>");
             }
             out.println("<br><a href='index.html'>Retour</a>");
             out.println("</body></html>");
    }

    

}
