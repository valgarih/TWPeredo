
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
        String filas = request.getParameter("filas");
        String columnas = request.getParameter("columnas");
        int filasi=Integer.parseInt(filas);
        int columnasi=Integer.parseInt(columnas);
        session.setAttribute("filas", filas);
        session.setAttribute("columnas", columnas);
                
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            out.println("<table border='1'>");
            for(int i=0; i<filasi; i++)
            {
            
            out.println("<tr>");/*tr => Table rout (columnas)*/
            for(int j=0; j<columnasi; j++){
            out.println("<td>'contenido"+i+j+"'<input type='text' name='contenido"+i+j+"'/></td>");
            out.println("<td>'hipervinculo"+i+j+"'<input type='text' name='hipervinculo"+i+j+"'/></td>");/*table de datos (filas)*/
            
            }
            out.println("</tr>");
            }
            out.println("</table>");
            out.println("<input type='submit'/>");
            out.println("</body>");
            out.println("</html>");
        
    }
}
