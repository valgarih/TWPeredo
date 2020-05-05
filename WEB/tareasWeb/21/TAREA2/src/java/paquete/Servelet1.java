package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class/*palabra reservada*/ Servelet1 extends/*crear herencia*/ HttpServlet/*Clase de donde estamos derivando*/ {

    @Override
    protected void doGet/*Hace una peticion al servidor*/(HttpServletRequest request, HttpServletResponse response)
            /*recive obejtos, los crea el servidor*/
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*Enviando tipo de contenido (tipo MIME)*/
        HttpSession session = request.getSession();
        String parametro = request.getParameter("parametro");
        int parametroi = Integer.parseInt(parametro);
        session.setAttribute("clave1", parametro);
        PrintWriter out = response.getWriter();
        /*identifica el navegador web, out genera el canal de comunicación del servidor 
        con el cliente. Out (canal de comunicación de tecto, ya que se envia solo texto en el ejemplo)*/
 /* String parametro=request.getParameter("parametro");
        int num = Integer.parseInt(parametro);*/

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servelet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='Servelet2' method='get'>");
        for (int i = 0; i < parametroi; i++) 
        out.println("VALOR A PROMEDIAR: " + i + "<input type='text' name='parametro" + i + "'/>");
        out.println("<input type='submit'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

    }

}
