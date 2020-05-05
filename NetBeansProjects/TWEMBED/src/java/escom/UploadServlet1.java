package escom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UploadServlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String parametro = request.getParameter("parametro");
        int filei = Integer.parseInt(parametro);
        session.setAttribute("clave1", parametro);

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet UploadServlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='UploadServlet' method='post' enctype = 'multipart/form-data'\">");
        for (int i = 0; i < filei; i++) {
            out.println("ARCHIVO: " + i + "<input type='file' name='file" + i + "'/>");
            out.println("<br />");

        }
        out.println("<input type='submit' value = 'Upload File'/>");
        out.println("</body>");
        out.println("</html>");

    }

}
