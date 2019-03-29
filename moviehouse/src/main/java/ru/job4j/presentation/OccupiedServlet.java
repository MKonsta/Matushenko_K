package ru.job4j.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import ru.job4j.controller.AccountsDB;
import ru.job4j.controller.PlacesDB;
import ru.job4j.service.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/occupie")
public class OccupiedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        JSONObject object = new JSONObject(json.toString());
        int id = object.getInt("id");
        String name = object.getString("name");
        int phone = object.getInt("phone");
        Account account = new Account(name, phone);

        String result;
        if (AccountsDB.getInstance().getExists(account)) {
            PlacesDB.getInstance().occupyPlace(id);
            result = "exist";
        } else {
            result = "notExist";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(result);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(toJson);
    }
}
