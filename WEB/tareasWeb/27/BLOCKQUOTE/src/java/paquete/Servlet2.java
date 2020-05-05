package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Servlet2 extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        String numero= (String)session.getAttribute("numero");
        int numeroi=Integer.parseInt(numero);
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");   
            out.println("</head>");
            out.println("<body>");
            out.println("<p>El blockquote se usa para escribir una cita textual o un párrafo exacto y que éste se diferencie del resto del texto. </p></br>");
            for(int i=0;i<numeroi;i++){
            
            out.println("<blockquote>"+request.getParameter("texto"+i)+"</blockquote></br>");
            
            }out.println("<p>El efecto que nos muestra es que crea unos espacios a la derecha y a la izquierda del texto, para que se diferencie de los demás.</p></br>");
            out.println("</body>");
            out.println("</html>");
    }

   
}



