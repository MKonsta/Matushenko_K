package ru.job4j.servlets.webarchitecture.presentation;

import ru.job4j.servlets.webarchitecture.logic.ValidateService;
import ru.job4j.servlets.webarchitecture.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        StringBuilder stringBuilder = new StringBuilder("<table>");
        for (User user : ValidateService.getValidateService().findAll()) {
            stringBuilder.append("<tr>"
                    + "<td>" + user + "</td>"
                    + "<td><a href='" + req.getContextPath()
                    + "/edit?id=" + user.getId() + "'>edit</a>"
                    + "</td>"
                    + "</tr>");
        }
        stringBuilder.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>List of Users</title>"
                + "</head>"
                + "<body>"
                + stringBuilder.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }
}
