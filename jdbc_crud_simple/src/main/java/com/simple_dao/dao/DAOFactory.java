package com.simple_dao.dao;

public class DAOFactory implements IDAOFactory {

    public static IDAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loading success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    public CarDAO getCarDAO() {
        return new CarJDBCDao();

    }

    public ClientDAO getClientDAO() {
        return new ClientJDBCDAO();
    }
}
