package ru.job4j.servlets.webarchitecturejsp.presentation;

import ru.job4j.servlets.webarchitecturejsp.logic.ValidateService;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editjsp")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = ValidateService.getValidateService().findById(id);
        req.setAttribute("id", id);
        req.setAttribute("name", user.getName());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("date", user.getCreateDate());
        resp.sendRedirect(req.getContextPath() + "/edit.jsp");
    }
}
