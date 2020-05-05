
package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        String rows = (String) session.getAttribute("clave1");
        int rowsi = Integer.parseInt(rows);
       String cols = (String) session.getAttribute("clave1");
        int colsi = Integer.parseInt(cols);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet2</title>");            
            out.println("</head>");
            out.println("<frameset rows='"+request.getParameter("tam1")+"'>");
            for(int i=0; i<rowsi; i++){
            out.println("<frame src='"+request.getParameter("num1"+i)+"'></frame>");    
            }
            out.println("<frameset cols='"+request.getParameter("tam2")+"'>");
            for(int i=0; i<colsi; i++){
            out.println("<frame src='"+request.getParameter("num2"+i)+"'></frame>");    
            }
            out.println("</frameset>");
            out.println("</frameset>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        
    }
}
