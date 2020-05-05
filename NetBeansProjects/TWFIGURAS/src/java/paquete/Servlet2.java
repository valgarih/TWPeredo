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
        String figura = (String) session.getAttribute("clave1");
        // int numero = Integer.parseInt(figura);
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet2</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<canvas id='myCanvas' width='600' height='800'></canvas>");
        out.println("<script>");
        
        out.println("var canvas = document.getElementById('myCanvas');");
        out.println("var context = canvas.getContext('2d');");
        out.println("context.beginPath();");
        if ("cuadrado".equals(figura) || "CUADRADO".equals(figura) || "RECTANGULO".equals(figura) || "rectangulo".equals(figura)) {
            out.println("context.fillRect(50,50," + request.getParameter("xf_AN") + "," + request.getParameter("yf_AL") + ");");
            out.println("context.stroke();");
        }
        else if ("circulo".equals(figura) || "CIRCULO".equals(figura)){
            out.println(" af = Math.PI*2;");
            out.println("context.arc(" + request.getParameter("Xi") + "," + request.getParameter("Yi") + "," + request.getParameter("radio")+",0,af);");
            out.println("context.fill();");
        }
        else if ("triangulo".equals(figura) || "TRIANGULO".equals(figura)){
            out.println("context.moveTo("+ request.getParameter("lado_1")+","+ request.getParameter("lado_1")+");");
            out.println("context.lineTo("+ request.getParameter("lado_1")+","+ request.getParameter("lado_2")+");");
            out.println("context.lineTo("+ request.getParameter("lado_2")+","+ request.getParameter("lado_1")+");");
            out.println("context.fill();");
        }
        
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");

    }

}
