package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class/*palabra reservada*/ Servelet1 extends/*crear herencia*/ HttpServlet/*Clase de donde estamos derivando*/

{
    @Override
    protected void doGet/*Hace una peticion al servidor*/(HttpServletRequest request, HttpServletResponse response)
            /*recive obejtos, los crea el servidor*/
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8"); /*Enviando tipo de contenido (tipo MIME)*/
        String parametro=request.getParameter("parametro");
        PrintWriter out = response.getWriter(); /*identifica el navegador web, out genera el canal de comunicación del servidor 
        con el cliente. Out (canal de comunicación de tecto, ya que se envia solo texto en el ejemplo)*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servelet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>HELLO WORLD : " +parametro+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        
    }

}
