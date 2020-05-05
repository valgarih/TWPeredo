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
import javax.servlet.ServletContext;

public class ServletXML_1 extends HttpServlet {

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
                       // String ruta = request.getRealPath("/");
                        ServletContext application =  this.getServletContext();
                        String ruta = application.getRealPath("/");
                        
			Element raiz = new Element("ROOT");
			Element hoja = new Element("hoja");
			hoja.setAttribute("numerodehojas", "4");
			hoja.setText("VALORDELNODO");/*va entre la etiqueta de pertura y cirre (valor del nodo)*/
			raiz.addContent(hoja);
  
			Document newdoc = new Document(raiz);
			XMLOutputter fmt = new XMLOutputter();
			FileWriter writer = new FileWriter(ruta+"pregunta2.xml");
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