package ru.job4j.servlets.webarchitecturejsp;

import org.junit.Test;
import ru.job4j.servlets.webarchitecturejsp.logic.ValidateService;
import ru.job4j.servlets.webarchitecturejsp.model.User;
import ru.job4j.servlets.webarchitecturejsp.presentation.AddUserServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

public class AddUserServletTest {
    @Test
    public void addUser() throws ServletException, IOException {

        AddUserServlet addUserServlet = new AddUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("q");
        when(request.getParameter("login")).thenReturn("q");
        when(request.getParameter("password")).thenReturn("q");
        when(request.getParameter("email")).thenReturn("q");
        when(request.getParameter("date")).thenReturn("q");
        when(request.getParameter("role")).thenReturn("user");

        addUserServlet.doPost(request, response);

        List<User> users = ValidateService.getValidateService().findAll();
        assertThat(users.get(1).getLogin(), is("q"));
    }
}
