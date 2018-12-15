package ru.job4j.servlets.webapparchitecture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class addUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String date = req.getParameter("date");

        try {
            int id = 0;
            writer.println("<h2>id: " + id + "; name: " + name + "; login: " + login + "; email: " + email + "; createDate: " + date + "</h2>");
            id = MemoryStore.getMemoryStore().add(new User(name, login, email, date)).getId();
        } finally {
            writer.close();
        }


    }
}
