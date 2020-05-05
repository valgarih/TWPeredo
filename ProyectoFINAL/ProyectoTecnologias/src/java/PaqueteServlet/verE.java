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

public class verE extends HttpServlet {

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
        out.println("<title>Responde la pregunta</title>");
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
        String examen = request.getParameter("examen");
        String pregunta = request.getParameter("pregunta");
        String texto = "", imagen = "", tipo = "", anterior = "", siguiente = "";

        try {

            File archivo = new File(this.getServletContext().getRealPath("/") + "/examen.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(archivo));
            Element raiz = doc.getDocumentElement();
            Node raizx = doc.getDocumentElement();
            //doc.getDocumentElement().getNodeName()); <- Es utilizado para obtener el nombre del nodo actual
            NodeList listaElem = doc.getElementsByTagName("id");
            int i;
            for (i = 0; i < listaElem.getLength(); i++) {

                Element eElement = (Element) listaElem.item(i);
                if (eElement.getAttribute("id").equals(examen)) {
                    NodeList listaPreg = eElement.getElementsByTagName("pregunta");
                    try {
                        File archivop = new File(this.getServletContext().getRealPath("/") + "/preguntas.xml");
                        DocumentBuilderFactory dbFactoryp = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilderp = dbFactoryp.newDocumentBuilder();
                        Document docp = dBuilderp.parse(new FileInputStream(archivop));
                        Element raizp = docp.getDocumentElement();
                        Node raizxp = docp.getDocumentElement();
                        //doc.getDocumentElement().getNodeName()); <- Es utilizado para obtener el nombre del nodo actual
                        NodeList listaElemp = docp.getElementsByTagName("id");
                        int j;
                        JSONArray list = new JSONArray();
                        JSONArray listcorr = new JSONArray();
                        for (int l = 0; l < listaPreg.getLength(); l++) {
                            Element preg = (Element) listaPreg.item(l);
                            if (pregunta == null) {
                                pregunta = preg.getAttribute("id");
                            }
                            
                            anterior = pregunta;
                            siguiente=pregunta;
                            
                            if (pregunta.equals(preg.getAttribute("id"))) {
                                Element p = (Element) listaPreg.item(l);
                                if(l>0){
                                    p = (Element) listaPreg.item(l-1);
                                    anterior= p.getAttribute("id");
                                }
                                if(l<listaPreg.getLength()-1){
                                    p = (Element) listaPreg.item(l+1);
                                    siguiente= p.getAttribute("id");
                                }
                                for (j = 0; j < listaElemp.getLength(); j++) {
                                    Element eElementp = (Element) listaElemp.item(j);
                                    if (eElementp.getAttribute("id").equals(preg.getAttribute("id"))) {
                                        texto = eElementp.getElementsByTagName("pregunta").item(0).getTextContent();
                                        imagen = eElementp.getElementsByTagName("multimedia").item(0).getTextContent();
                                        Element x = (Element) eElementp.getElementsByTagName("pregunta").item(0);
                                        tipo = x.getAttribute("tipo");
                                       NodeList resp = eElementp.getElementsByTagName("respuesta");
                                        for (int k = 0; k < resp.getLength(); k++) {
                                            Element e = (Element) resp.item(k);
                                            list.add(e.getTextContent());
                                            listcorr.add(e.getAttribute("correcta"));
                                        }
                                        break;
                                    }
                                }

                                JSONObject obj = new JSONObject();
                                obj.put("id", pregunta);
                                obj.put("tipo", tipo);
                                obj.put("pregunta", texto);
                                obj.put("resp", list);
                               obj.put("corr", listcorr);
                                obj.put("multimedia", imagen);
                                obj.put("anterior", anterior);
                                obj.put("siguiente", siguiente);
                                obj.put("examen", examen);

                                try {
                                    FileWriter archivoJson = new FileWriter(this.getServletContext().getRealPath("/") + "/pregunta.json");
                                    // FileWriter archivoJson = new FileWriter("datos.json"); //tengo que usar el getrealpath 
                                    // String path = this.getServletContext().getRealPath("/")+"/json//datos.json";
                                    archivoJson.write(obj.toJSONString());
                                    archivoJson.flush();
                                    archivoJson.close();

                                    out.println("<div id=\"root\"></div>");
                                    if (tipo.equals("pattern")) {
                                        out.println("<script src=\"Vexamen.js\" type=\"text/babel\"> </script>");
                                    } 

                                } catch (Exception ex) {
                                    out.println(ex);
                                    out.println("ERROR AL MOMENTO DE CREAR JSON");
                                }
                                break;
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println("ERROR AL MOMENTO DE LEER EL ARCHIVO PreguntasXML. Se lee: id=" + pregunta + " pregunta=" + texto + " imagen=" + imagen + " tipo=" + tipo);
                    }

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR AL MOMENTO DE LEER EL ARCHIVO ExamenesXML");
        }
        
        out.println("</body>");
        out.println("</html>");
    }

}
