package PaqueteServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Iterator;
import javax.servlet.ServletContext;


public class LectorExamen extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"); 
            out.println("<title>TablaExamen</title>");
            out.println("<meta charset= 'utf-8' />");
            out.println("<meta name='viewport' content='user-scalable=0, initial-scale=1, minimum-scale=1, width=device-width, height=device-height'>");
            out.println("<script src='https://unpkg.com/react@latest/umd/react.development.js' crossorigin='anonymous'></script>");
            out.println("<script src='https://unpkg.com/react-dom@latest/umd/react-dom.development.js' crossorigin='anonymous'></script>");
            out.println("<script src='https://unpkg.com/@material-ui/core@3.9.3/umd/material-ui.development.js' crossorigin='anonymous'></script>");
            out.println("<script src='https://unpkg.com/babel-standalone@latest/babel.min.js' crossorigin='anonymous'></script>");
            
            out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400,500' />");
            out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons' />");
            out.println("<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>");
           
            out.println("</head>");
            out.println("<body>");
           try {

                File archivo = new File(this.getServletContext().getRealPath("/")+"/examen.xml");
               // File archivo = new File("C:/Users/itzel/Desktop/6TO SEMESTRE/tecnologías para la web/ProyectoTecnologias/usuario.xml");//tengo que usar el getrealpath
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(archivo);
                doc.getDocumentElement().normalize();

                NodeList listaElem = doc.getElementsByTagName("id");
                JSONArray list = new JSONArray();
                for (int i = 0; i < listaElem.getLength(); i++) {
                       
                    Element eElement = (Element) listaElem.item(i);
                    
                     list.add(eElement.getAttribute("id"));
                          
                }
                   //response.sendRedirect("LoginCorrectoIncorrecto?id="+id+"&nombre="+nombre);
                    JSONObject obj = new JSONObject();
                    obj.put("ids", list);
 
                    try{
                            FileWriter archivoJson = new FileWriter(this.getServletContext().getRealPath("/")+"/examen.json");
 
                            archivoJson.write(obj.toJSONString());
                            archivoJson.flush();
                            archivoJson.close();
                            
                            out.println("<div id=\"root\"></div>");
                            out.println("<script src=\"tableExam.js\" type=\"text/babel\"> </script>");

                    }catch(Exception ex){
                            out.println(ex);
                    }
            }catch (Exception e) {
              e.printStackTrace();
              out.println("ERROR AL MOMENTO DE LEER EL ARCHIVO XML");
            }
        
            out.println("</body>");
            out.println("</html>");
    }

}
