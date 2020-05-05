package paquete;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.List;
import javax.servlet.ServletContext;
import org.jdom.input.SAXBuilder;

public class ServletXML_1 extends HttpServlet {

          @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        try
        {
        SAXBuilder builder = new SAXBuilder();/*parseador (simple api XML)*/
        File archivoXML = new File("archivoXML.xml"); /*esta en la carpeta webpage*/
        Document documento=builder.build(archivoXML);/*construye el archivo XML*/
        Element raiz=documento.getRootElement(); /*obtiene el elemento raiz*/
        List lista=raiz.getChildren("name"); /*obten todos los hijos*/
            for(int i=0;i<lista.size();i++)
            {
             Element elemento=(Element)lista.get(i); 
             String cadena=elemento.getTextTrim();/* recorta los espacios en blanco*/
             out.println(cadena);
            }
        }
        catch(JDOMException e)
        {
        e.printStackTrace();
        }
        }

}
