package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; //Importar librerias.
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class Servlet1 extends HttpServlet //public.- Modificador de clase
{ 

    @Override //notación que cancela el metodo
    protected void doGet(HttpServletRequest request, HttpServletResponse response) //request va del cliente al servidor, response de servidor a cliente. Modificador de acceso le decimos quien puede entrar a nuestro metodo
        //El servidor web instancia los objetos request y response.
            throws ServletException, IOException { //Este metodo puede lanzar dos tipos de excepciones
            response.setContentType("text/html;charset=UTF-8");  //MIME Es lo que se va a mandar
            String rute = request.getParameter("rute");
            PrintWriter out = response.getWriter(); //genera el canal de respuesta desde el srvidor hasta el cliente,(hay 2 tipo texto y binario) este es tipo texto. 
            out.println("<!DOCTYPE html>"); //Se empieza a imprimir la página.
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");        
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultados</h1>");
            Servlet1 validador = new Servlet1();
            try{
                try{
                    validador.checkConforme(rute);
                }
                catch (JDOMException e){
                    out.println(rute + " <h3>NO esta bien conformado</h3> <br/>");
                    out.println(e.getMessage());
                    //System.exit(0);
                }
                out.println(rute + " <h3>Esta bien formado</h3> <br/>");
                try{
                    validador.checkValidation(rute);
                }
                catch(JDOMException e){
                    out.println(rute + " <h3>NO es valido</h3> <br/>");
                    out.println(e.getMessage());
                    //System.exit(0);
                }
                out.println(rute + " <h3>Es valido</h3> <br/>");
            }
            catch(IOException e){
                out.println("<h3>No se pudo revisar</h3> <br/>" + rute);
                out.println("<h3>Porque: </h3>" + e.getMessage() + "<br/>");
            }
            out.println("</body>");
            out.println("</html>");
    }
    public void checkConforme (String sURL) throws JDOMException, IOException{
        SAXBuilder builder = new SAXBuilder();
        builder.build(sURL);
    }
    
    public void checkValidation(String sURL) throws JDOMException, IOException{
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.build(sURL);
    }
}