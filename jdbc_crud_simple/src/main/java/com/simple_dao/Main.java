package com.simple_dao;

import com.simple_dao.dao.CarDAO;
import com.simple_dao.dao.ClientDAO;
import com.simple_dao.dao.DAOFactory;
import com.simple_dao.dao.IDAOFactory;
import com.simple_dao.entity.Car;

public class Main {
    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();
        ClientDAO clientDAO = factory.getClientDAO();


//        clientDAO.add(new Client("Tirehin", 51, "996-45-78-43"));
        //        System.out.println(clientDAO.getAllClients());
//        for (Client client : clientDAO.getAllClients()) {
//            System.out.println(client);
//        }




//        carDAO.add(new Car("Lada", "Kalina", 1000));
//        carDAO.add(new Car("Lada", "Nerjaveika", 1500));
//        carDAO.add(new Car("Jiguli", "Shesterka", 500));
//        carDAO.updatePrice(900, 40);
//        carDAO.remove(1);
//        carDAO.remove("Lada");
//        System.out.println(carDAO.getAll());
    }
}
