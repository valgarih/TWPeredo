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

public class VistaP2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        out.println("<title>MensajeLogin</title>");
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
        String pregunta = request.getParameter("pregunta");
        String examen=request.getParameter("examen");
        String texto = "", imagen="", tipo= "";
        try {

            File archivo = new File(this.getServletContext().getRealPath("/") + "/preguntas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(archivo));
            Element raiz = doc.getDocumentElement();
            Node raizx = doc.getDocumentElement();
            //doc.getDocumentElement().getNodeName()); <- Es utilizado para obtener el nombre del nodo actual
            NodeList listaElem = doc.getElementsByTagName("id");
            int i;
            JSONArray list = new JSONArray();
            for (i = 0; i < listaElem.getLength(); i++) {

                Element eElement = (Element) listaElem.item(i);
                if (eElement.getAttribute("id").equals(pregunta)) {
                    texto = eElement.getElementsByTagName("pregunta").item(0).getTextContent();
                    imagen = eElement.getElementsByTagName("multimedia").item(0).getTextContent();
                    Element x = (Element) eElement.getElementsByTagName("pregunta").item(0);
                    tipo = x.getAttribute("tipo");
                    NodeList resp = eElement.getElementsByTagName("respuesta");
                    for (int j = 0; j < resp.getLength(); j++) {
                        Element e = (Element) resp.item(j);
                        list.add(e.getTextContent());
                    }
                    break;
                }
            }
            JSONObject obj = new JSONObject();
            obj.put("id", pregunta);
            obj.put("tipo", tipo);
            obj.put("pregunta", texto);
            obj.put("resp", list);
            obj.put("multimedia", imagen);
            obj.put("examen",examen);
            try {
                FileWriter archivoJson = new FileWriter(this.getServletContext().getRealPath("/") + "/preguntas2.json");
                // FileWriter archivoJson = new FileWriter("datos.json"); //tengo que usar el getrealpath 
                // String path = this.getServletContext().getRealPath("/")+"/json//datos.json";
                archivoJson.write(obj.toJSONString());
                archivoJson.flush();
                archivoJson.close();

                out.println("<div id=\"root\"></div>");
                if(tipo.equals("pattern")){
                    out.println("<script src=\"vistapreguntas2.js\" type=\"text/babel\"> </script>");
                }

            } catch (Exception ex) {
                out.println(ex);
                out.println("ERROR AL MOMENTO DE CREAR JSON");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR AL MOMENTO DE LEER EL ARCHIVO XML. Se lee: id="+pregunta+" pregunta="+texto+" imagen="+imagen+" tipo="+tipo);
        }
        /*try{
            sleep(4000);
            }catch (InterruptedException ex) {
                System.out.println("Interrumpido");
            }*/
        out.println("</body>");
        out.println("</html>");
    }

}
