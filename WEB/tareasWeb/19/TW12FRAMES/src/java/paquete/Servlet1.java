
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cols = request.getParameter("cols");
        String izquierdo = request.getParameter("izquierdo");
        String derecho = request.getParameter("derecho");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            
            out.println("<frameset cols='"+cols+"' frameborder='yes' border='1'>");
            out.println("<frame src='"+izquierdo+"' />");
            out.println("<frame src='"+derecho+"' />");
            out.println("</frameset>");
            out.println("<noframes>");
            out.println("<body>");
            out.println("<h1>tu navegador no soporta los frames</h1>");
            out.println("</body>");
            out.println("</noframes>");
            out.println("</html>");

    }

}
