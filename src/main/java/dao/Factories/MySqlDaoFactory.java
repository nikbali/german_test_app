package dao.Factories;


import dao.Interfaces.DAOFactory;
import dao.Interfaces.GenericDAO;
import dao.MySQLThemeDAO;
import dao.MySqlQuestionDao;
import dao.Interfaces.UserDAO;
import dao.MySqlTestDAO;
import model.Question;
import model.Test;
import model.Theme;

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
    private String connectionUrl = "jdbc:mysql://mysql99.1gb.ru:3306/gb_db_nikbali?autoReconnect=true&useSSL=false&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
    private Connection cons;
    private static MySqlDaoFactory instance;

    private MySqlDaoFactory() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        cons = DriverManager.getConnection(connectionUrl, userName, password);
    }
    public static MySqlDaoFactory getInstance() throws SQLException, ClassNotFoundException
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
    public MySqlQuestionDao getQuestionDAO() {
        return new MySqlQuestionDao(cons);
    }

    @Override
    public GenericDAO<Test> getTestDAO() {
        return new MySqlTestDAO(cons);
    }

    @Override
    public GenericDAO<Theme> getThemeDAO() {
        return new MySQLThemeDAO(cons);
    }

    @Override
    public UserDAO getUserDAO() {
        //TODO написать реализацию
        return null;
    }
}
