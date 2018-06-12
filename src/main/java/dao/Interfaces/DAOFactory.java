package dao.Interfaces;

import model.Question;
import model.Test;
import model.Theme;

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
    /** Возвращают DAO объекты для Question*/
    GenericDAO<Test> getTestDAO();
    /** Возвращают DAO объекты для Question*/
    GenericDAO<Theme> getThemeDAO();
    /**Возвращают DAO объекты для User */
    UserDAO getUserDAO();
}
