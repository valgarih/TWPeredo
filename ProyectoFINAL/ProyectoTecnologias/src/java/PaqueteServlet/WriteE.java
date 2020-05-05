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
import static java.lang.Thread.sleep;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WriteE extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //String email = request.getParameter("email");
        //String password = request.getParameter("password");
        HttpSession session = request.getSession();

        //el que recibe ->String atributo1 = (String) session.getAttribute("clave1");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Pregunta</title>");
        out.println("<meta charset= 'utf-8' />");
        out.println("<meta name='viewport' content='user-scalable=0, initial-scale=1, minimum-scale=1, width=device-width, height=device-height'>");
        out.println("<script src='https://unpkg.com/react@latest/umd/react.development.js' crossorigin='anonymous'></script>");
        out.println("<script src='https://unpkg.com/react-dom@latest/umd/react-dom.development.js' crossorigin='anonymous'></script>");
        out.println("<script src='https://unpkg.com/@material-ui/core/umd/material-ui.development.js' crossorigin='anonymous'></script>");
        out.println("<script src='https://unpkg.com/babel-standalone@latest/babel.min.js' crossorigin='anonymous'></script>");
        out.println("<script src='https://unpkg.com/@material-ui/core/umd/material-ui.production.min.js' crossorigin='anonymous'></script>");
        out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400,500' />");
        out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons' />");
        out.println("<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>");

        out.println("</head>");
        out.println("<body>");
        String idexamen = request.getParameter("idexamen");
        
        try {

            File archivo = new File(this.getServletContext().getRealPath("/") + "/examen.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(archivo));
            Element raiz = doc.getDocumentElement();
           
            Node raizx =doc.getDocumentElement();
            NodeList listaElem = doc.getElementsByTagName("id");
            
            for (int i = 0; i < listaElem.getLength(); i++) {                      
                Element eElement = (Element) listaElem.item(i);
                if(eElement.getAttribute("id").equals(idexamen)){
                    Node node = listaElem.item(i);
                    raizx.removeChild(node);
                    //Node node = listaElem.item(i);
                    // eElement.getParentNode().removeChild(eElement);                       
                } 
                    
            }
            
            Element item = doc.createElement("id");
            item.setAttribute("id",idexamen);
            String[] preguntas;
            preguntas = request.getParameterValues("NombrePregunta");
             for(int i=0; i<preguntas.length; i++){
                Element pregunta = doc.createElement("pregunta");
                pregunta.setAttribute("id", preguntas[i]);
               
                item.appendChild(pregunta);
            }
            raiz.appendChild(item);
            
            doc.getDocumentElement().normalize();
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(doc), new StreamResult(archivo));
            /*doc.normalize();
                StringWriter stw = new StringWriter();
                Transformer serializer = TransformerFactory.newInstance().newTransformer();
                serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
                serializer.transform(new DOMSource(doc), new StreamResult(stw));*/

        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR AL MOMENTO DE LEER EL ARCHIVO XML");
        }
        try {
            sleep(4500);
            response.sendRedirect("LectorExamen");
        } catch (InterruptedException ex) {
            out.println("Interrumpido");
        }
   
        
        out.println("</body>");
        out.println("</html>");
    }

}
