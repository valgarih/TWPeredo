
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Servlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String numero=request.getParameter("num");
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
            out.println("<form action='Servlet1' method='get'>");
            out.println(" Selecciona el archivo data<input type='text' name='data'/> </br>");
            for(int i=0; i<num;i++){
            
            out.println("param name"+i+" <input type='text' name='pn_"+i+"'/ > </br>");
            out.println("param value"+i+" <input type='text' name='pv_"+i+"'/></br>");
            }
            out.println("<input type='submit'/>");
             out.println("<form>");
            out.println("</body>");
            out.println("</html>");
    }

   
}



