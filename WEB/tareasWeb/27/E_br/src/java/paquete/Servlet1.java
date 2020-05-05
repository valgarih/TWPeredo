
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String num = request.getParameter("num");
        int numi = Integer.parseInt(num);
        session.setAttribute("clave1", num);
        int contador=0;
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<form action='Servlet2' method='get'>");
            for(int i=0; i<numi; i++){
                out.println("Espacio "+i+"<br>");
                contador++;
            }
            out.println("El numero de espacios fue :"+contador+" ");
            //out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        
    }

}
