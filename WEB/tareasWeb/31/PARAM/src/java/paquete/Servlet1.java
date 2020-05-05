
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
        
        HttpSession session= request.getSession();
     String numero=(String) session.getAttribute("numero");
        int num=Integer.parseInt(numero);
        PrintWriter out = response.getWriter();
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<object data='"+request.getParameter("data")+"'  type='application/x-shockwave-flash' width='180' height='350'>");
            
            for(int i=0;i<num;i++){
            out.println("<param  name='"+request.getParameter("pn_"+i)+"' value='"+request.getParameter("pv_"+i)+"'/>");
            }
            out.println("</object>");
             
            out.println("</body>");
            out.println("</html>");
    }

   
}



