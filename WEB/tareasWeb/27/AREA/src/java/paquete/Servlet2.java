
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
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<img src='"+request.getParameter("imagen")+"' usemap='#map1' width='145' height='126'>");
            
            out.println("<map name='#map1'>");
            for(int i=0;i<numeroi;i++){
            out.println("<area href='"+request.getParameter("hiper_"+i)+"' alt='El navegador"+i+"' shape='circle' coords='"+request.getParameter("coordxf_"+i)+","+request.getParameter("coordyi_"+i)+","+request.getParameter("coordyf_"+i)+"'>");
           
            }
            out.println("</map>");
            

            out.println("</body>");
            out.println("</html>");
    }

   
}



