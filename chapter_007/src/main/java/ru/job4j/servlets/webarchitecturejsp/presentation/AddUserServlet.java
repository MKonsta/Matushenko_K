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

@WebServlet("/addjsp")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        String role = req.getParameter("role");
        User user = new User(name, login, password, email, date, role);
        if (ValidateService.getValidateService().add(user)) {
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
