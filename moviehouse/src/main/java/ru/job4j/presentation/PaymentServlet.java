package ru.job4j.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.controller.TempPlaceHolder;
import ru.job4j.service.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(TempPlaceHolder.getINSTANCE().getTempPlace());
        TempPlaceHolder.getINSTANCE().setPlaceNull();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(toJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String str = bufferedReader.readLine();
        System.out.println(str);

        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(str, Account.class);
    }
}
