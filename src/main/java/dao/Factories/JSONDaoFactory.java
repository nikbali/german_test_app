package dao.Factories;

import dao.Interfaces.DAOFactory;
import dao.Interfaces.GenericDAO;
import dao.Interfaces.UserDAO;
import model.Question;
import model.Test;
import model.Theme;

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
    public GenericDAO<Test> getTestDAO() {
        return null;
    }

    @Override
    public GenericDAO<Theme> getThemeDAO() {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }
}
