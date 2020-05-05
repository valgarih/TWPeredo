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
import static java.lang.System.out;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.jdom.input.SAXBuilder;

public class ServletXML_1 extends HttpServlet {

    String usuario1;
    String contraseña1;
    String usuario2;
    String contraseña2;

   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ///metodo para el formulario
        String id= request.getParameter("id");
        String password= request.getParameter("password");        
        HttpSession sesion=request.getSession();
        sesion.setAttribute("userName",id);
        response.setContentType("text/html;charset=UTF-8");
       ///archivoXML
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getRealPath("/xml2");
        PrintWriter out = response.getWriter();

        try {
            SAXBuilder builder = new SAXBuilder();/*parseador (simple api XML)*/
            File Nuevo = new File(ruta + "\\Nuevo.xml");
            /*esta en la carpeta webpage*/
            Document documento = builder.build(Nuevo);/*construye el archivo XML*/
            Element raiz = documento.getRootElement();/*obtiene el elemento raiz*/
            Element nodo1 = raiz.getChild("usuario1");
            Element nodo2 = raiz.getChild("usuario2");
            Element userName1 = nodo1.getChild("userName");
            Element password1 = nodo1.getChild("password");
            usuario1 = userName1.getAttributeValue("name");
            contraseña1 = password1.getAttributeValue("name");

            Element userName2 = nodo2.getChild("userName");
            Element password2 = nodo2.getChild("password");
            usuario2 = userName2.getAttributeValue("name");
            contraseña2 = password2.getAttributeValue("name");
            
   /*     out.println(usuario1);
        out.println(contraseña1);
        out.println(usuario2);
        out.println(contraseña2);*/
        } catch (JDOMException e) {
        }
        
         if(usuario1.equals(request.getParameter("id"))&&(contraseña1.equals(request.getParameter("password"))))
            response.sendRedirect("principal");
         else if (usuario2.equals(request.getParameter("id"))&&(contraseña2.equals(request.getParameter("password"))))	
            response.sendRedirect("principal");
         else // username/password not validated
             response.sendRedirect("incorrecto");
        
    }
       

}
