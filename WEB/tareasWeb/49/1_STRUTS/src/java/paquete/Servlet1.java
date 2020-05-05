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

public class Servlet1 extends HttpServlet {      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		try
		{
			Element raiz = new Element("struts-config");
			Element hoja0 = new Element("form-beans");
                        Element hoja1= new Element("global-exceptions");
                        Element hoja2= new Element("global-forwards");
                        Element hoja2hija=new Element("forward");
                        Element hoja3= new Element("action-mappings");
                        Element hoja3hija0= new Element("action");
                        Element hoja4= new Element("controller");
                        Element hoja5= new Element("message-resources");
                        Element hoja6= new Element("plug-in");
                        Element hoja6hija0= new Element("set-property");
                        Element hoja6hija1= new Element("set-property");
                        Element hoja7= new Element("plug-in");
                        Element hoja7hija=new Element("set-property");
                        
                        hoja2hija.setAttribute("name", "welcome");
                        hoja2hija.setAttribute("path", "/Welcome.do");
                        hoja3hija0.setAttribute("path", "/Welcome");
                        hoja3hija0.setAttribute("forward", "/welcomeStruts.jsp");
                        hoja4.setAttribute("processorClass","org.apache.struts.tiles.TilesRequestProcessor");
                        hoja5.setAttribute("parameter","com/myapp/struts/ApplicationResource");
                        hoja6hija0.setAttribute("property","definitions-config");
                        hoja6hija0.setAttribute("value","/WEB-INF/tiles-defs.xml");
                        hoja6hija1.setAttribute("property","moduleAware");
                        hoja6hija1.setAttribute("value","true");
                        hoja6.setAttribute("className","org.apache.struts.tiles.TilesPlugin");
                        hoja7hija.setAttribute("property","pathnames");
                        hoja7hija.setAttribute("value","/WEB-INF/validator-rules.xml,/WEB-INF/validation.xml");
                        hoja7.setAttribute("className","org.apache.struts.validator.ValidatorPlugIn");
                        
                        hoja0.setText("     ");
                        hoja1.setText("     ");
			raiz.addContent(hoja0);
                        raiz.addContent(hoja1);
                        raiz.addContent(hoja2);
                        raiz.addContent(hoja3);
                        raiz.addContent(hoja4);
                        raiz.addContent(hoja5);
                        raiz.addContent(hoja6);
                        raiz.addContent(hoja7);
                        
                        
                        hoja2.addContent(hoja2hija);
                        hoja3.addContent(hoja3hija0);
                        hoja6.addContent(hoja6hija0);
                        hoja6.addContent(hoja6hija1);
                        hoja7.addContent(hoja7hija);
                        
                        
			Document newdoc = new Document(raiz);
			XMLOutputter fmt = new XMLOutputter();
			FileWriter writer = new FileWriter("c:\\carpeta\\pregunta.xml");
			fmt.output(newdoc, writer);
            writer.flush();
            writer.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	}


}
