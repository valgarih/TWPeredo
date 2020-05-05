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
public class Servlet2 extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        
        String filas=(String)session.getAttribute("filas");
        String columnas=(String)session.getAttribute("columnas");
        int filasi=Integer.parseInt(filas);
        int columnasi=Integer.parseInt(columnas);
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1'>");
            
            for(int i=0; i<filasi;i++){
                out.println("<tr>");
                for(int j=0;j<columnasi;j++)
                out.println("<td><a href='"+request.getParameter("hipervinculo"+i+j)+"'>"+request.getParameter("contenido"+i+j)+"</a></td>");
                out.println("</tr>"); 
            }
           
            out.println("</table>");
            out.println("</body>");
            out.println("</body>");
            out.println("</html>");
    }

   
}