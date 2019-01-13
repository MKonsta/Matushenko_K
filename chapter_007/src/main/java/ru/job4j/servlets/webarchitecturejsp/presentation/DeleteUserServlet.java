package ru.job4j.servlets.webarchitecturejsp.presentation;

import ru.job4j.servlets.webarchitecturejsp.logic.DBStore;
import ru.job4j.servlets.webarchitecturejsp.logic.ValidateService;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deletejsp")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", req.getParameter("id"));
        getServletContext().getRequestDispatcher("/WEB-INF/delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ValidateService.getValidateService().delete(id);
        resp.sendRedirect(req.getContextPath() + "/usersjsp");
    }

    @Override
    public void destroy() {
        try {
            DBStore.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
