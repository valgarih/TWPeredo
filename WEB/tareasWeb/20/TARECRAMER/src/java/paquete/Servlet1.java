
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
        String X1 = request.getParameter("X1");
        double x11 = Integer.parseInt(X1);
        session.setAttribute("clave1", X1);
        
        String X2 = request.getParameter("X2");
        double x22 = Integer.parseInt(X2);
        session.setAttribute("clave1", X2);
        
        String Y1 = request.getParameter("Y1");
        double y11 = Integer.parseInt(Y1);
        session.setAttribute("clave1", Y1);
        
        String Y2 = request.getParameter("Y2");
        double y22 = Integer.parseInt(Y2);
        session.setAttribute("clave1", Y2);
        
        String R1 = request.getParameter("R1");
        double r11 = Integer.parseInt(R1);
        session.setAttribute("clave1", R1);
        
        String R2 = request.getParameter("R2");
        double r22 = Integer.parseInt(R2);
        session.setAttribute("clave1", R2);
        
        double sistema;
        double resulx;
        double resuly;
        double auxx;
        double auxy;
        PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            int hola = -14-18;
            sistema = ((x11)*(y22))-((y11)*(x22));
            resulx = (r11*y22)-(y11*r22);
            resuly = ((x11)*(r22))-((r11)*(x22));
            auxx = (resulx)/(sistema);
            auxy = (resuly)/(sistema);
            out.println("El resultado es :  X = "+auxx+", Y = "+auxy+"<br/>");
            out.println("</body>");
            out.println("</html>");
        
    }

}
