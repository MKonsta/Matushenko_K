package ru.job4j.servlets.webapparchitecture;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String id = req.getParameter("id");

        try {
            writer.println("<h2>");
            writer.println("<p>" + MemoryStore.getMemoryStore().findById(Integer.parseInt(id)) + " removed from DB" + "</p>");
            writer.println("</h2>");
            MemoryStore.getMemoryStore().delete(Integer.parseInt(id));
        } finally {
            writer.close();
        }
    }
}
