package dao;

import dao.Interfaces.GenericDAO;
import model.Question;
import model.Test;
import model.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlTestDAO implements GenericDAO<Test> {
    private final Connection connection;

    public MySqlTestDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public int create(Test object) throws SQLException {
        return 0;
    }

    @Override
    public Test getByPK(int pk) throws SQLException {
        String sql = "SELECT * FROM Test WHERE id = ?;";
        String sql_tematics = "SELECT * FROM Theme WHERE test_id = ?;";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, pk);
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        Test cur_test = new Test(resultSet.getInt("id"),resultSet.getString("name"));

        //поиск по темам
        PreparedStatement stm2 = connection.prepareStatement(sql_tematics);
        stm2.setInt(1, pk);
        ResultSet resultSetTheme = stm2.executeQuery();
        while (resultSetTheme.next())
        {
            cur_test.addThema(new Theme(resultSetTheme.getInt("id"), resultSetTheme.getString("name"), cur_test.getName()));
        }
        ps.close();
        stm2.close();
        return cur_test;
    }

    @Override
    public void update(Test object) throws SQLException {

    }

    @Override
    public boolean delete(int pk) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Test> getAll() throws SQLException {
        ArrayList<Test> list_tests= new ArrayList<Test>();
        String sql = "SELECT * FROM Test";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next())
        {
            Test test = new Test(resultSet.getInt("id"), resultSet.getString("name"));
            list_tests.add(test);
        }
        ps.close();
        return list_tests;
    }
}
