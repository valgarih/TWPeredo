
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
        String numero=request.getParameter("numero");
        HttpSession session= request.getSession();
        session.setAttribute("numero", numero);
        int num=Integer.parseInt(numero);
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            for(int i=0; i<num;i++){
            out.println("Nombre del botón <input type='text' name='nb_"+i+"'/>");
            out.println("Contenido del botón <input type='text' name='cb_"+i+"'/>");
            out.println("Servlet a que sera dirigido <input type='text' name='sb_"+i+"'/>");
            
            }
            out.println("<input type='submit'/>");
             out.println("<form>");
            out.println("</body>");
            out.println("</html>");
    }

   
}



