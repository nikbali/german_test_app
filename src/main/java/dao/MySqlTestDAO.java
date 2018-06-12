package dao;

import dao.Interfaces.GenericDAO;
import model.Test;

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
        return null;
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
