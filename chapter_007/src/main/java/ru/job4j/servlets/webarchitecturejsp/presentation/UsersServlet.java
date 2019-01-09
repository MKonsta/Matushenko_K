package ru.job4j.servlets.webarchitecturejsp.presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * После запускаа TomCat Приложение можно запустить вбив в браузер http://localhost:8082/chapter_007/usersjsp
 */
@WebServlet("/usersjsp")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
    }
}
