
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
        HttpSession session = request.getSession();
        String filas= (String) session.getAttribute("filas");
        String columnas= (String) session.getAttribute("columnas");
        int filasi=Integer.parseInt(filas);
        int columnasi=Integer.parseInt(columnas);
                
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet2</title>");            
            out.println("</head>");
            out.println("<body>");            
            out.println("<table border='1'>");
            for(int i=0; i<filasi; i++)
            {            
            out.println("<tr>");/*tr => Table rout (columnas)*/
            for(int j=0; j<columnasi; j++){
            out.println("<td><img src='"+request.getParameter("imagen"+i)+"' width='110' height='110'><a href='"+request.getParameter("hipervinculo"+i+j)+"'>"+request.getParameter("contenido"+i+j)+"</a></td>");/*table de datos (filas)*/
           // out.println("<map name='shape'>");
            //out.println("<area href='"+request.getParameter("hipervinculo"+i+j)+"' alt='square' shape='square' coords='165,50,55,160' >"+request.getParameter("contenido"+i+j)+"");            
            //out.println("</map>");
            }
            out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
    }

}
