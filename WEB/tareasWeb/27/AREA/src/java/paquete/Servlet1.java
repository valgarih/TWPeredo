
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
        String numero= request.getParameter("numero");
        int numeroi= Integer.parseInt(numero);
        HttpSession session= request.getSession();
        session.setAttribute("numero", numero);
       
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            
            out.println("Elige la imagen que deseas divir:<input type='text' name='imagen'/></br>");
            out.println("Las coordenadas estan en el rango de 0 hasta 146</br>");
            for(int i=0; i<numeroi;i++){
            out.println("Hipervínculo:"+i+"<input type='text' name='hiper_"+i+"'/></br>");
            
            out.println("Coordenadas xf:"+i+"<input type='text' name='coordxf_"+i+"'/></br>");
            out.println("Coordenadas yi:"+i+"<input type='text' name='coordyi_"+i+"'/></br>");
            out.println("Coordenadas yf:"+i+"<input type='text' name='coordyf_"+i+"'/></br>");
            }
            out.println("<input type='submit'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
    }

   
}



