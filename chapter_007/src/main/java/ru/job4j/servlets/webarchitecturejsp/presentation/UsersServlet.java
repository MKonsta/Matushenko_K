package ru.job4j.servlets.webarchitecturejsp.presentation;

import ru.job4j.servlets.webarchitecturejsp.logic.DBStore;
import ru.job4j.servlets.webarchitecturejsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * После запускаа TomCat Приложение можно запустить вбив в браузер http://localhost:8082/chapter_007/usersjsp
 */
@WebServlet("/usersjsp")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", ValidateService.getValidateService().findAll());
        HttpSession session = req.getSession();
        String role = ValidateService.getValidateService().findByLogin((String) session.getAttribute("login")).getRole();
        if (role.equals("admin")) {
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
        } else if (role.equals("user")) {
            getServletContext().getRequestDispatcher("/WEB-INF/usersLimited.jsp").forward(req, resp);
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
