package ru.job4j.servlets.webapparchitecture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SelectAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        try {
            writer.print("<h2>");
            for (User user : MemoryStore.getMemoryStore().findAll()) {
                writer.print("<p>" + user + "</p>");
            }
            writer.print("</h2>");
        } finally {
            writer.close();
        }
    }
}
