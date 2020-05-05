
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
 * @author valery garibay
 */
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
            out.println("Llena los datos de cada tarea");
            out.println("<form action='Servlet2' method='get'>");
            for(int i=0;i<num;i++){
            out.println("tarea"+i+" valor"+i+" <input type='text' name='v_"+i+"'/></br>");
            out.println("tarea"+i+"  max"+i+"<input type='text' name='m_"+i+"'/></br>");
            out.println("tarea"+i+"  contenido"+i+"<input type='text' name='c_"+i+"'/></br>");
            }
            out.println("<input type='submit'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
    }

   
}

