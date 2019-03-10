package ru.job4j.servlets.webarchitecturejsp.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.webarchitecturejsp.logic.CountriesAndCities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/countryservlet")
public class CitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CountriesAndCities countriesAndCities = CountriesAndCities.getCities();
        Set<String> countries = new HashSet<>();
        for (String str : CountriesAndCities.getCityMap().keySet()) {
            countries.add(str);
        }
        String toJson = objectMapper.writeValueAsString(countries);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(toJson);
    }
}
