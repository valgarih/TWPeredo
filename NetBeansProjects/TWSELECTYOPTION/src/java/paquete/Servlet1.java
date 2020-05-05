
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
        HttpSession session = request.getSession();
        String num= request.getParameter("num");
        session.setAttribute("clave1", num);
        int numi = Integer.parseInt(num);
        PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
           out.println("Ingrese los nombres se sus option</br>");
            for (int i =0; i<numi; i++){
                out.println("<input type='text' name='valor"+i+"'></br>");
            }
            out.println("<input type='submit'>");
            //out.println("</select>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        
    }

}
