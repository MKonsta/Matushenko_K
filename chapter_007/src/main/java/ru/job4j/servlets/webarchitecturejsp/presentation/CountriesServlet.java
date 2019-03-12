package ru.job4j.servlets.webarchitecturejsp.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.webarchitecturejsp.logic.CitiesDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/countryservlet")
public class CountriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(CitiesDB.getInstance().getCountries());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(toJson);
    }
}
