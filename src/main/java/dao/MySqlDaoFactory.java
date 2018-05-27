package dao;


import model.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Фабричный класс для создания DAO объектов при подключении к MySQL
 * Реализован также паттерн Singleton, чтобы в программе создавался лишь один объект подключения к БД
 * @author Nikita Balily
 */
public class MySqlDaoFactory implements DAOFactory {

    private String userName = "gb_db_nikbali";
    private String password = "3c52724479a";
    private String connectionUrl = "jdbc:mysql://mysql99.1gb.ru:3306/gb_db_nikbali?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private Connection cons;
    private static MySqlDaoFactory instance;

    private MySqlDaoFactory() throws SQLException
    {
        cons = DriverManager.getConnection(connectionUrl, userName, password);
    }
    public static MySqlDaoFactory getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return cons;
    }

    @Override
    public GenericDAO<Question> getQuestionDAO() {
        return new MySqlQuestionDao(cons);
    }

    @Override
    public UserDAO getUserDAO() {
        //TODO написать реализацию
        return null;
    }
}
