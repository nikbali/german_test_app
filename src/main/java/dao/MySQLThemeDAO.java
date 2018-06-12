package dao;

import dao.Interfaces.GenericDAO;
import model.Test;
import model.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MySQLThemeDAO implements GenericDAO<Theme> {

    private final Connection connection;

    public MySQLThemeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Theme object) throws SQLException {
        return 0;
    }

    @Override
    public Theme getByPK(int pk) throws SQLException {
        String sql = "SELECT * FROM Theme WHERE id = ?;";
        MySqlTestDAO test_dao = new MySqlTestDAO(connection);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, pk);
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        Theme theme = new Theme(resultSet.getInt("id"), resultSet.getString("name"),test_dao.getByPK(resultSet.getInt("test_id")));
        ps.close();
        return theme;
    }

    @Override
    public void update(Theme object) throws SQLException {

    }

    @Override
    public boolean delete(int pk) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Theme> getAll() throws SQLException {
        MySqlTestDAO test_dao = new MySqlTestDAO(connection);
        ArrayList<Theme> list_tests= new ArrayList<Theme>();
        String sql = "SELECT * FROM Theme";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next())
        {
            int test_id = resultSet.getInt("test_id");
            Test test = test_dao.getByPK(test_id);
            Theme theme = new Theme(resultSet.getInt("id"), resultSet.getString("name"), test);
            list_tests.add(theme);
        }
        ps.close();
        return list_tests;
    }
}
