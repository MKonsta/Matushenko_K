package ru.job4j.servlets.webarchitecture.presentation;

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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder stringBuilder = new StringBuilder("<table>");
        for (User user : ValidateService.getValidateService().findAll()) {
            stringBuilder.append("<tr><td>" + user + "</tr></td>");
        }
        stringBuilder.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Create user</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='post'>"
                + "Name : <input type='text' name='name'/>"
                + " Login : <input type='text' name='login'/>"
                + " e-mail : <input type='text' name='email'/>"
                + " Create date : <input type=text' name='date'/>"
                + "<input type='submit'>"
                + "</form>"
                + "</br>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (ValidateService.getValidateService().add(new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), req.getParameter("date")))) {
            UsersServlet usersServlet = new UsersServlet();
            usersServlet.doGet(req, resp);
        } else {
            PrintWriter writer = resp.getWriter();
            try {
                writer.println("Login or E-mail is wrong!");
            } finally {
                writer.close();
            }
        }

    }
}
