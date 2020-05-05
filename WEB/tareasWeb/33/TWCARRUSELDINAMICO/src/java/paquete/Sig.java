
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sig extends HttpServlet {
    private ArrayList A= new ArrayList();
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session= request.getSession();
        A= (ArrayList)session.getAttribute("Array");
        String yi= String.valueOf(session.getAttribute("y"));
        int y=Integer.parseInt(yi);
        y=y+1;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
       if(y==0){
       response.sendRedirect("Servlet3");
       }else if(y>=A.size()){
             response.sendRedirect("Servlet3");
       }else if(y==(A.size()-1)){
       out.println("<img src='"+A.get(y)+"'></br>");
            out.println("<a href='Ant'>Anterior</a>               ");
       }else{
            out.println("<img src='"+A.get(y)+"'></br>");
            out.println("<a href='Ant'>Anterior</a>                <a href='Sig'>Siguiente</a>");
       }
            session.setAttribute("y", y);
            out.println("</body>");
            out.println("</html>");
    }

   
}



