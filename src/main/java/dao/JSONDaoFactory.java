package dao;

import model.Question;

import java.sql.Connection;
import java.sql.SQLException;

public class JSONDaoFactory implements DAOFactory {
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public GenericDAO<Question> getQuestionDAO() {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }
}
