package ru.job4j.servlets.webarchitecture.presentation;

import ru.job4j.servlets.webarchitecture.logic.ValidateService;
import ru.job4j.servlets.webarchitecture.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        String id = req.getParameter("id");

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Update User</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/edit?id='id'' method=post>"
                + "Name : <input type='text' name='name' value='" + ValidateService.getValidateService().findById(Integer.parseInt(id)).getName() + "'/>"
                + "Login : <input type='text' name='login' value='" + ValidateService.getValidateService().findById(Integer.parseInt(id)).getLogin() + "'/>"
                + "e-mail : <input type='text' name='email' value='" + ValidateService.getValidateService().findById(Integer.parseInt(id)).getEmail() + "'/>"
                + "Create date : <input type=text' name='date' value='" + ValidateService.getValidateService().findById(Integer.parseInt(id)).getCreateDate() + "'/>"
                + "<input type='submit'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ValidateService.getValidateService().update(Integer.parseInt(req.getParameter("id")),
                new User(req.getParameter("name"), req.getParameter("login"),
                        req.getParameter("email"), req.getParameter("date")));
        doGet(req, resp);
    }
}
