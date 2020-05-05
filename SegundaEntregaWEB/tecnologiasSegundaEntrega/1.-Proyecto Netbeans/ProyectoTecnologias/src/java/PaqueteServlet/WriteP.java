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

public class WriteP extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //String email = request.getParameter("email");
        //String password = request.getParameter("password");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Pregunta</title>");
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
        
        String idpregunta = request.getParameter("idpregunta");
        String texto = request.getParameter("texto");
        String tipo = request.getParameter("tipo");
        String link = request.getParameter("link");
        int i;
        try {

            File archivo = new File(this.getServletContext().getRealPath("/") + "/preguntas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(archivo));
            Element raiz = doc.getDocumentElement();
            //doc.getDocumentElement().getNodeName()); 
            Node raizx =doc.getDocumentElement();
            NodeList listaElem = doc.getElementsByTagName("id");
            
            for (i = 0; i < listaElem.getLength(); i++) {                      
                Element eElement = (Element) listaElem.item(i);
                if(eElement.getAttribute("id").equals(idpregunta)){
                    Node node = listaElem.item(i);
                    raizx.removeChild(node);
                    //Node node = listaElem.item(i);
                    // eElement.getParentNode().removeChild(eElement);                       
                } 
                    
            }

            Element item = doc.createElement("id");
            item.setAttribute("id",idpregunta);
            Element multimedia = doc.createElement("multimedia");
            multimedia.appendChild(doc.createTextNode(link));
            item.appendChild(multimedia);
            Element pregunta = doc.createElement("pregunta");
            pregunta.setAttribute("tipo", tipo);
            pregunta.appendChild(doc.createTextNode(texto));
            item.appendChild(pregunta);
            for(i=1; i<=8; i++){
                String respx = request.getParameter("resp"+i);
                String respc =  request.getParameter("resp"+i+"C");
                if(!respx.equals("")){
                    Element resp = doc.createElement("respuesta");
                    resp.setAttribute("id", Integer.toString(i));
                    resp.setAttribute("calificacion",respc);
  
                    resp.appendChild(doc.createTextNode(respx));
                    item.appendChild(resp);
                }
            }
            raiz.appendChild(item);
            
            doc.getDocumentElement().normalize();
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(doc), new StreamResult(archivo));


        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR AL MOMENTO DE LEER EL ARCHIVO XML");
        }
        try {
            sleep(4000);
            response.sendRedirect("LectorPregunta");
        } catch (InterruptedException ex) {
            out.println("Fallo");
        }
   
        
        out.println("</body>");
        out.println("</html>");
    }

}
