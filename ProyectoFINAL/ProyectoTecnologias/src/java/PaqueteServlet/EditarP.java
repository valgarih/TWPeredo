
package PaqueteServlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EditarP extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditarP</title>"); 
            
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
            
           String idPregunta = request.getParameter("pregunta");
            String multimedia = "";
            String tipo = "";
            String textoPregunta = "";
            String idRespuesta = "";
            String textoRespuesta = "";
            String PuntajeRespuesta = "";
         //   Boolean valorBooleano = false;
           try {

                File archivo = new File(this.getServletContext().getRealPath("/")+"/preguntas.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(archivo);
                doc.getDocumentElement().normalize();
                
                NodeList listaElem = doc.getElementsByTagName("id");
                JSONArray listTextoRespuesta = new JSONArray();
                JSONArray listPuntaje = new JSONArray();
            //    JSONArray listValorBooleano = new JSONArray();
                int i,j=0;
                for (i = 0; i < listaElem.getLength(); i++) {
                       
                    Element eElement = (Element) listaElem.item(i);
                    
                    if(eElement.getAttribute("id").equals(idPregunta)){
                        Element elementoPregunta = (Element) eElement.getElementsByTagName("pregunta").item(0);
                        multimedia = eElement.getElementsByTagName("multimedia").item(0).getTextContent();
                        textoPregunta = eElement.getElementsByTagName("pregunta").item(0).getTextContent();
                        tipo = elementoPregunta.getAttribute("tipo");
                        NodeList listaHijos = eElement.getElementsByTagName("respuesta");
                        for(j=0; j < listaHijos.getLength();j++){
                            Element eElementHijo = (Element) listaHijos.item(j);
                            idRespuesta = eElementHijo.getAttribute("id");
                            PuntajeRespuesta = eElementHijo.getAttribute("calificacion");
                            textoRespuesta = eElementHijo.getTextContent();
                      //      valorBooleano =  Boolean.parseBoolean( eElementHijo.getAttribute("correcta"));
                            listTextoRespuesta.add(eElementHijo.getTextContent());
                            listPuntaje.add(eElementHijo.getAttribute("calificacion"));
                      //      listValorBooleano.add(valorBooleano);
                        }
                        for(j=j; j < 8;j++){
                            listTextoRespuesta.add("");
                            listPuntaje.add("");
                 //           listValorBooleano.add(false);
                        }
                    }                       
                }
                String cantidadRespuestas = Integer.toString(j);
                //no se pone ni tipo ni multimedia;
                JSONObject obj = new JSONObject();
                obj.put("idPregunta", idPregunta);
                obj.put("textoPregunta", textoPregunta);
                obj.put("textoRespuesta", listTextoRespuesta);
                obj.put("PuntajeRespuesta", listPuntaje);
           //     obj.put("valorBooleano", listValorBooleano);
                obj.put("cantidadRespuestas",cantidadRespuestas);
                
                    try{
                            FileWriter archivoJson = new FileWriter(this.getServletContext().getRealPath("/")+"/edicionPregunta.json");

                            archivoJson.write(obj.toJSONString());
                            archivoJson.flush();
                            archivoJson.close();
                            
                            
                            out.println("<div id=\"root\"></div>");
                            if(tipo.equals("pattern")){
                                out.println("<script src=\"EditarPattern.js\" type=\"text/babel\"> </script>");
                            }
                            else{
                            }
                            

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
