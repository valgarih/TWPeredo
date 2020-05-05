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
      
        HttpSession session = request.getSession();
        String rows= request.getParameter("rows");
        session.setAttribute("clave1",rows);
        int rowsi = Integer.parseInt(rows);
        
        String cols= request.getParameter("cols");
        session.setAttribute("clave1",cols);
        int colsi = Integer.parseInt(cols);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
  
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            
            out.println("<h1>Ingresa el tamaño de tus filas</h1><br>");
            out.println("FILAS <input type='text' name='tam1'/><br>");
            out.println("<br>Ingrese el URL de una pagina o documento1.html o documento2.html<br><br> ");
            for (int i=0; i<rowsi; i++){
                out.println("Fila "+i+"<input type='text' name='num1"+i+"'/><br>");
            }
            
            out.println("<h1>Ingresa el tamaño de tus columnas</h1>");
            out.println("COLUMNAS <input type='text' name='tam2'/><br>");
            out.println("Ingrese el URL de una pagina o documento1.html o documento2.html<br><br> ");
            for (int i=0; i<colsi; i++){
                out.println("Columna "+i+"<input type='text' name='num2"+i+"'/><br>");
            }
            
            out.println("<input type='submit'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

    }

}
