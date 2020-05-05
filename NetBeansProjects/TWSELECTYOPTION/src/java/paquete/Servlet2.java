
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String num = (String) session.getAttribute("clave1");
        int numi = Integer.parseInt(num);
        
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script>");
            out.println("function ddlselect(){");
            
            out.println("var d=document.getElementById('ddselect');");
            out.println("var displaytext=d.options[d.selectedIndex].text;");
            out.println("document.getElementById('txtvalue').value=displaytext;");
            
            out.println("}");
            out.println("</script>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<select name='ddselect' id='ddselect' onchange='ddlselect();' size='"+numi+"' multiple>");
            out.println("<option>-- Select --</option>");
            for (int i=0; i< numi; i++){                
                out.println("<option value='"+request.getParameter("valor"+i)+"'>"+request.getParameter("valor"+i)+"</option>");
            }
            out.println("<input type='text' id='txtvalue'>");
            out.println("</select>");
            out.println("</body>");
            out.println("</html>");
        
    }

}
