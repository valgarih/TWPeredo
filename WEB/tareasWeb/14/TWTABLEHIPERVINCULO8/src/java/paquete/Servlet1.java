/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Itzel A
 */
public class Servlet1 extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session= request.getSession();
       String filas=request.getParameter("filas");
        String columnas=request.getParameter("columnas");
        int filasi=Integer.parseInt(filas);
        int columnasi=Integer.parseInt(columnas);
        session.setAttribute("filas", filas);
        session.setAttribute("columnas", columnas);
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            
            for(int i=0; i<filasi;i++){
                for(int j=0;j<columnasi;j++)   {    
                    out.println("Contenido"+i+j+"<input type='text' name='contenido"+i+j+"'/>");
                    out.println("hipervinculos"+i+j+"<input type='text' name='hipervinculo"+i+j+"'/>");
                    out.println("</br>");
                   
            }
           
            }
           
            out.println("<input type='submit' />");
            out.println("</body>");
            out.println("</body>");
            out.println("</html>");
    }

   
}