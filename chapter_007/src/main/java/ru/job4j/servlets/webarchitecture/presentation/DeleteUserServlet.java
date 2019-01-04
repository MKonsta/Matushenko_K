package ru.job4j.servlets.webarchitecture.presentation;

import ru.job4j.servlets.webarchitecture.logic.ValidateService;
import ru.job4j.servlets.webarchitecture.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteUserServlet extends HttpServlet {
    private ValidateService service = ValidateService.getValidateService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        String id = req.getParameter("id");
        User byId = service.findById(Integer.valueOf(id));

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Delete User</title>"
                + "</head>"
                + "<body>"
                + "<h1 align='center'>Delete user with id: " + req.getParameter("id") + "?</h1>"
                + "<table align='center'>"
                + "     <tr>"
                + "         <td>"
                + "             <form action='" + req.getContextPath() + "/delete' method='post'>"
                + "             <input type='hidden' name='id' value='" + req.getParameter("id") + "'>"
                + "             <button type='submit'>Yes</button>"
                + "             </form>"
                + "         </td>"
                + "         <td>"
                + "             <form action='" + req.getContextPath() + "/list'>"
                + "             <button type='submit'>No</button>"
                + "             </form>"
                + "         </td>"
                + "     </tr>"
                + "</table>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (ValidateService.getValidateService().delete(Integer.valueOf(req.getParameter("id")))) {
            String path = req.getContextPath() + "/list";
            resp.sendRedirect(path);
        } else {
            PrintWriter writer = resp.getWriter();
            try {
                writer.println("<h1>Error</h1>");
            } finally {
                writer.close();
            }
        }

        doGet(req, resp);
    }
}
