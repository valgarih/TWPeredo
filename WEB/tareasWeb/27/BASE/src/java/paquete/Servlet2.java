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
            out.println("<base href='"+request.getParameter("base")+"' target='_blank'>");            
            out.println("</head>");
            out.println("<body>");
            out.println("Estas palabras son la referencia a los archivos dentro con la url absoluta</br>");
            for(int i=0;i<numeroi;i++){
            out.println("<img src='"+request.getParameter("base_"+i)+"></br>");
            }
            out.println("</body>");
            out.println("</html>");
    }

   
}



