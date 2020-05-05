package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servelet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String  parametro = (String) session.getAttribute("clave1");
        int parametroi = Integer.parseInt(parametro);
        int datos;
        double suma = 0;
        double fi;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servelet2</title>");
        out.println("</head>");
        out.println("<body>");
        for (int i = 0; i < parametroi; i++) {
            //out.println("<h1>" + request.getParameterValues("datos"+i)+ "</h1>");
            //out.println("<h1>"+parametroi+ "</h1>");
            datos = Integer.parseInt(request.getParameter("parametro"+i));
            
             suma= suma+datos;
            
            //out.println("<h1>" + request.getParameter("parametro"+i)+ "</h1>");
        }
        fi= suma/parametroi;
        out.println("<h1>"+fi+ "</h1>");
        out.println("");
        out.println("</body>");
        out.println("</html>");


    }

}
