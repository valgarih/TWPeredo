
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
        response.setContentType("text/html;charset=UTF-8");
        String flas = request.getParameter("filas");
        int filasi=Integer.parseInt(flas);
        String columnas = request.getParameter("columnas");
        int columnasi=Integer.parseInt(columnas);
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1'>");
            for(int i=0; i<filasi; i++)
            {
            
            out.println("<tr>");/*tr => Table rout (columnas)*/
            for(int j=0; j<columnasi; j++)
            out.println("<td>dato</td>"); /*table de datos (filas)*/
            out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        
    }
}
