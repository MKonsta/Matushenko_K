package ru.job4j.servlets.webarchitecturejsp;

import org.junit.Test;
import ru.job4j.servlets.webarchitecturejsp.logic.ValidateService;
import ru.job4j.servlets.webarchitecturejsp.model.User;
import ru.job4j.servlets.webarchitecturejsp.presentation.AddUserServlet;
import ru.job4j.servlets.webarchitecturejsp.presentation.DeleteUserServlet;
import ru.job4j.servlets.webarchitecturejsp.presentation.EditServlet;

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

        when(request.getParameter("name")).thenReturn("Stepan");
        when(request.getParameter("login")).thenReturn("step");
        when(request.getParameter("password")).thenReturn("1");
        when(request.getParameter("email")).thenReturn("st@mail.ru");
        when(request.getParameter("date")).thenReturn("777");
        when(request.getParameter("role")).thenReturn("user");

        addUserServlet.doPost(request, response);

        List<User> users = ValidateService.getValidateService().findAll();
        assertThat(users.get(2).getLogin(), is("step"));
    }

    @Test
    public void updateUser() throws ServletException, IOException {
        EditServlet edit = new EditServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Ivan");
        when(request.getParameter("login")).thenReturn("Ivan");
        when(request.getParameter("password")).thenReturn("1");
        when(request.getParameter("email")).thenReturn("eeee");
        when(request.getParameter("date")).thenReturn("1111");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("role")).thenReturn("user");

        edit.doPost(request, response);

        List<User> users = ValidateService.getValidateService().findAll();
        assertThat(users.get(1).getEmail(), is("eeee"));

//        for (User user : users) {
//            System.out.println(user);
//        }
    }

    @Test
    public void delete() throws ServletException, IOException {
        DeleteUserServlet del = new DeleteUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        del.doPost(request, response);

        List<User> users = ValidateService.getValidateService().findAll();
        assertThat(users.size(), is(1));
    }

}
