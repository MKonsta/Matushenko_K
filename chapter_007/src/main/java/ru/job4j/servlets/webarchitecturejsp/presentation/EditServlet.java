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
        getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        if (ValidateService.getValidateService().update(id, new User(name, login, email, date))) {
            resp.sendRedirect(req.getContextPath() + "/usersjsp");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }

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
