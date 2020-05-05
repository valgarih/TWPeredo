
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int numero= Integer.parseInt(request.getParameter("numero"));
        HttpSession session= request.getSession();
        session.setAttribute("numero", numero);
        PrintWriter out = response.getWriter();
   
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method ='post' enctype = 'multipart/form-data' >"); 
            
            for(int i=0;i<numero;i++){
            out.println("Archivo");
            out.println(i);
            out.println("<input type ='file' name ='file"+i+"' size='45' />");
            out.println("</br>");
            }  
            out.println("<input type='submit'/>");
         
            
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
    }

   
}
