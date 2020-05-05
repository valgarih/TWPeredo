package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String figura = request.getParameter("figura");
        HttpSession session = request.getSession();
        session.setAttribute("clave1", figura);
        // int lineas = Integer.parseInt(figura);
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action= 'Servlet2' method='get'>");
        if ("cuadrado".equals(figura) || "CUADRADO".equals(figura) || "RECTANGULO".equals(figura) || "rectangulo".equals(figura)) {
            out.println("INGRESE LAS MEDIDAS DE SU CUADRADO</br>");
            out.println("ANCHO: <input type='text' name='xf_AN'/></br>");
            out.println("ALTO: <input type='text' name='yf_AL'/></br>");
          //  out.println("<input type='submit' />");
        }
        else if ("circulo".equals(figura) || "CIRCULO".equals(figura)) {
            out.println("INGRESE LAS MEDIDAS DE SU CIRCULO</br>");
            out.println("RADIO: <input type='text' name='radio'/></br>");
            out.println("INICIO Xi: <input type='text' name='Xi'/></br>");
            out.println("INICIO Yi: <input type='text' name='Yi'/></br>");
            
        }
        else if ("triangulo".equals(figura) || "TRIANGULO".equals(figura)) {
            out.println("INGRESE LAS MEDIDAS DE SU TRIANGULO</br>");
            out.println("LADO 1: <input type='text' name='lado_1'/></br>");
            out.println("LADO 2: <input type='text' name='lado_2'/></br>");
            //out.println("<input type='submit' />");
        }
        else 
            out.println("NO INGRESO NINGUNA FIGURA");
            out.println("<input type='submit' />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        

    }

}
