package paquete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Servlet implementation class Servlet1
 */
public class ServletXML_1 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		try
		{
			Element raiz = new Element("struts-config");
			Element hoja1 = new Element("form-beans");
                        Element hoja2 = new Element("global-exceptions");
                        Element hoja3 = new Element("gglobal-forwards");
                        Element hoja3_1 = new Element("forward");
                        Element hoja4 = new Element("action-mappings");
                        Element hoja4_1 = new Element("action");
                        Element hoja5 = new Element("controller");
                        Element hoja6 = new Element("message-resources");
                        Element hoja7 = new Element("plug-in");
                        Element hoja7_1 = new Element("set-property");
                        Element hoja7_11 = new Element("set-property");
                        Element hoja8 = new Element("plug-in");
                        Element hoja8_1 = new Element("set-property");
                        
                        
			hoja3_1.setAttribute("name", "welcome");
                        hoja3_1.setAttribute("path", "/Welcome.do");
                        
                        hoja4_1.setAttribute("path", "/Welcome");
                        hoja4_1.setAttribute("forward", "/welcomeStruts.jsp");
                        
                        hoja5.setAttribute("processorClass", "org.apache.struts.tiles.TilesRequestProcessor");
                        
                        hoja6.setAttribute("parameter", "com/myapp/struts/ApplicationResource");
                       
                        hoja7.setAttribute("className", "org.apache.struts.tiles.TilesPlugin");
                       
                        hoja7_1.setAttribute("property", "definitions-config");
                        hoja7_1.setAttribute("value", "/WEB-INF/tiles-defs.xml");
                        hoja7_11.setAttribute("property", "moduleAware");
                        hoja7_11.setAttribute("value", "true");
                        
                        hoja8.setAttribute("className", "org.apache.struts.validator.ValidatorPlugIn");
                       
                        hoja7_1.setAttribute("property", "pathnames");
                        hoja7_1.setAttribute("value", "/WEB-INF/validator-rules.xml,/WEB-INF/validation.xml");
                       
                        
			//hoja.setText("VALORDELNODO");/*va entre la etiqueta de pertura y cirre (valor del nodo)*/
			hoja1.setText("  ");
                        hoja2.setText("  ");
                        raiz.addContent(hoja1);
                        raiz.addContent(hoja2);
                        raiz.addContent(hoja3);
                        hoja3.addContent(hoja3_1);
                        raiz.addContent(hoja4);
                        hoja4.addContent(hoja4_1);
                        raiz.addContent(hoja5);
                        raiz.addContent(hoja6);
                        raiz.addContent(hoja7);
                        hoja7.addContent(hoja7_1);
                        hoja7.addContent(hoja7_11);
                        raiz.addContent(hoja8);
                        hoja8.addContent(hoja8_1);
  
			Document newdoc = new Document(raiz);
			XMLOutputter fmt = new XMLOutputter(); /*clase que nos permite dar la salida de XML*/
                        FileWriter writer = new FileWriter("c:\\carpeta1\\pregunta3.xml");
			//FileWriter writer = new FileWriter("c:\\carpeta1\\pregunta3.xml");
			fmt.output(newdoc, writer);
            writer.flush();/*pone todo el xml completo*/
            writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	}


}
