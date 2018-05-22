package dao;


import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImplements implements QuestionDAO {
    private final Connection connection;

    public QuestionDaoImplements(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Question create() {
        return null;
    }

    @Override
    public Question read(int key) throws SQLException {
        String sql = "SELECT * FROM Question WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, key);
        ResultSet rs = stm.executeQuery();
        rs.next();
        Question question = new Question(rs.getInt("id"),rs.getString("text"),rs.getString("image_path"));
        return question;
    }

    @Override
    public void update(Question question) {

    }

    @Override
    public void delete(Question question) {

    }

    @Override
    public List<Question> getAll() throws SQLException {
        String sql = "SELECT * FROM Question;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Question> list = new ArrayList<Question>();
        while (rs.next()) {
            Question g = new Question(rs.getInt("id"),rs.getString("text"),rs.getString("image_path"));
            list.add(g);
        }
        return list;

    }

    public static void main(String[] args) {
        //DaoFactory daoFactory = new MySqlDaoFactory();
        List<Question> list;
        try (Connection con = DBConnect.getConnecttion()) {
            QuestionDaoImplements dao = new QuestionDaoImplements(con);
            list = dao.getAll();
            System.out.println(list);
        }
        catch (SQLException ex)
        {
            ex.getMessage();
        }
    }
}
