
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
        String numero=request.getParameter("num");
        HttpSession session= request.getSession();
        session.setAttribute("num", numero);
        int num= Integer.parseInt(numero);
        
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("Llena el siguiente formulario");
            out.println("<form action='Servlet2' method='get'>");
            for(int i=0;i<num;i++){
            out.println("pagina frame"+i+" <input type='text' name='fp_"+i+"'/></br>");
            out.println("contenido frame"+i+"<input type='text' name='fc_"+i+"'/></br>");
            }
            out.println("<input type='submit'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
    }

   
}

