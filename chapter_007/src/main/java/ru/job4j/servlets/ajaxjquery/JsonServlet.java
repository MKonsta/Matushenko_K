package ru.job4j.servlets.ajaxjquery;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/jsonservlet")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(PersonStorage.getMap());
        req.setAttribute("personMap", toJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
//        Map<Integer, Person> personMap = new ConcurrentHashMap<>();
        Person person = objectMapper.readValue(sb.toString(), Person.class);
        PersonStorage.getMap().put(PersonStorage.getMap().size() + 1, person);

//        String toJson = objectMapper.writeValueAsString(personMap);
//        resp.setContentType("text/json");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        System.out.println(toJson);
//        writer.append(toJson);
//        writer.flush();
    }

}