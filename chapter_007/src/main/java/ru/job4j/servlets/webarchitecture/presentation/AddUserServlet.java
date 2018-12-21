package ru.job4j.servlets.webarchitecture.presentation;

import ru.job4j.servlets.webarchitecture.logic.DBStore;
import ru.job4j.servlets.webarchitecture.logic.ValidateService;
import ru.job4j.servlets.webarchitecture.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String date = req.getParameter("date");

        try {
            writer.println("<h2>name: " + name + "; login: " + login + "; email: "+ email + "; createDate: " + date + "</h2>");
            ValidateService.getValidateService().add(new User(name, login, email, date));
        } finally {
            writer.close();
        }
    }
}
