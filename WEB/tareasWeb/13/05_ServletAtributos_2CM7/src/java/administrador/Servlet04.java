package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       response.setContentType("text/html;charset=UTF-8");
       HttpSession session = request.getSession();
       String valor1 = request.getParameter("clave01");
       
       /*String atributo =  ""+ session.getAttribute("CLAVE1_ATRIBUTO"); debe castear*/
       String atributo1 = (String)session.getAttribute("clave01_atributo");
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet04</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> "+atributo1+"</h1>");
        out.println("</body>");
        out.println("</html>");
        session.removeAttribute("clave01_atributo"); /*Remueve atributo de memoria sin cerrar navegador*/
    }
}
