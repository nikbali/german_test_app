package dao;

import dao.Interfaces.GenericDAO;
import model.Test;
import model.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MySQLThemeDAO implements GenericDAO<Theme> {

    private final Connection connection;

    public MySQLThemeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Theme object) throws SQLException {
        String sql_create_thema = "INSERT into Theme(name, test_id) VALUES(?,'1')";
        String query_last = "SELECT * FROM Theme ORDER BY id desc limit 1";
        PreparedStatement ps;
        ps = connection.prepareStatement(sql_create_thema);
        ps.setString(1, object.getName());
        ps.executeUpdate();
        PreparedStatement stm2 = connection.prepareStatement(query_last);
        ResultSet rs = stm2.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        ps.close();
        return id;
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
