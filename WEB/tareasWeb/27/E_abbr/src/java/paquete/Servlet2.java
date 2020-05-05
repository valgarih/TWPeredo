
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session =request.getSession();
        String num= (String) session.getAttribute("clave1");
        int numi=Integer.parseInt(num);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Servlet2 at " + request.getContextPath() + "</h1>");
            for(int i=0; i<numi; i++){
                out.println("Abreviacion "+i+" : <abbr title='"+request.getParameter("nom"+i)+"'>"+request.getParameter("abrev"+i)+"</abbr><br>");
            }
            out.println("</body>");
            out.println("</html>");
        
    }

}
