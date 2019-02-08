package ru.job4j.servlets.ajaxjquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/jsonservlet")
public class JsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String gender = req.getParameter("sex");
        String discription = req.getParameter("discription");
        try {
            writer.print("<p>HHHHHHHHHHH</p>");
            writer.print(req.getParameter(name));
            writer.print(req.getParameter(surname));
            writer.print(req.getParameter(gender));
            writer.print(req.getParameter(discription));
        } finally {
            writer.close();
        }
    }
}
