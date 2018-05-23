package dao;

import model.Answer;
import model.Question;

import java.sql.Connection;

/**
 * Реализация паттерна Factory Method для Data Access Object
 * @author Nikita Balily
 */
public interface DAOFactory {
    /** Получаем instansce подключения к БД*/
    Connection getConnection();

    /** Возвращают DAO объекты для Question и Answer*/
    GenericDAO<Question> getQuestionDAO(Connection connect);
    GenericDAO<Answer> getAnswerDAO(Connection connect);

    /**Возвращают DAO объекты для User */
    UserDAO getUserDAO(Connection connect);
}
