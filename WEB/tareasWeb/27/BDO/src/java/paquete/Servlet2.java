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
            out.println("<body>");out.println("<p>");
            for(int i=0;i<numeroi;i++){
            
            out.println("<bdo dir='"+request.getParameter("dir"+i)+"'>"+request.getParameter("texto"+i)+"</bdo></br>");
            
            }out.println("</p>");
            out.println("</body>");
            out.println("</html>");
    }

   
}



