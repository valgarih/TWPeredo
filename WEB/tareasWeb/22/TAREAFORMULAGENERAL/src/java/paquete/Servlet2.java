
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
       String  num = (String) session.getAttribute("clave1");
       int numi = Integer.parseInt(num);
       
       PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            double mult;
            double raiz;
            double resta;
            double suma;
            double div1 [] = new double[numi];
            double div2 [] = new double[numi];
            for (int i=0; i<numi; i++){
                mult = (Integer.parseInt(request.getParameter("b"+i))*Integer.parseInt(request.getParameter("b"+i)))-4*(Integer.parseInt(request.getParameter("a"+i))*Integer.parseInt(request.getParameter("c"+i)));
                raiz = Math.sqrt(mult);
                resta = (-Integer.parseInt(request.getParameter("b"+i)))-raiz;
                suma = (-Integer.parseInt(request.getParameter("b"+i)))+raiz;
                div1[i] = resta / (2*Integer.parseInt(request.getParameter("a"+i))); 
                div2[i] = suma / (2*Integer.parseInt(request.getParameter("a"+i))); 
                if(numi!=0){
                    out.println("Tu respuesta es de la ecuacion "+i+": X1 :"+div1[i]+", X2 :"+div2[i]+"<br/>");
                }
            }
            
            out.println("</body>");
            out.println("</html>");
        
    }

}
