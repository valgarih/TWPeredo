package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Servlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String archivo = request.getParameter("archivo");
        String ruta = request.getRealPath("/");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            this.checkConforme(ruta+"\\"+archivo);
            out.println(archivo +"Archivo bien conformado");
        } catch (JDOMException e) {
            out.println(archivo +"No esta bien conformado");
        }
        try {
            this.checkValido(ruta+"\\"+archivo);
            out.println(archivo +"Archivo valido");
        } catch (JDOMException e) {
            out.println(archivo +"Archivo no valido");
        }
        
        out.println("</body>");
        out.println("</html>");

    }

    public void checkConforme(String sURL)
            throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.build(sURL);
    }
// Método que comprueba la validez con el DTD

    public void checkValido(String sURL)
            throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.build(sURL);
    }

}
