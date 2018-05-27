package dao;

import model.Question;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Реализация паттерна Factory Method для Data Access Object
 * @author Nikita Balily
 */
public interface DAOFactory {
    /** Получаем instansce подключения к БД*/
    Connection getConnection() throws SQLException;

    /** Возвращают DAO объекты для Question*/
    GenericDAO<Question> getQuestionDAO();
    /**Возвращают DAO объекты для User */
    UserDAO getUserDAO();
}
