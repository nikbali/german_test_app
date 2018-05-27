package dao;

import model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MySqlQuestionDao implements GenericDAO<Question> {
    private final Connection connection;

    public MySqlQuestionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Question question) {
        String sql_answer_create = "INSERT into Answer() VALUES(?,?,?,?,?,?)";
        String sql = "INSERT into Question(text, image_path) value(?,?)";
        int id = -1;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, question.getTextOfQuestion());
            ps.setString(2, question.getStringOfImageForQuestion());
            ps.executeUpdate();

            if(question.getAnswers().size() != 0)
            {
                PreparedStatement stm2 = connection.prepareStatement("SELECT * FROM Question WHERE id = ?;");
            }


            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public Question getByPK(int key) throws SQLException {
        String sql_question = "SELECT * FROM Question WHERE id = ?;";
        String sql_answers = "SELECT * FROM Answer WHERE question_id = ?;";

        PreparedStatement stm = connection.prepareStatement(sql_question);
        stm.setInt(1, key);
        ResultSet rs = stm.executeQuery();
        rs.next();
        Question question = new Question(rs.getString("text"),rs.getString("image_path"));
        question.setId(rs.getInt("id"));
        PreparedStatement stm2 = connection.prepareStatement(sql_answers);
        stm2.setInt(1, key);
        ResultSet resultSetAnswer = stm2.executeQuery();
        while (resultSetAnswer.next())
        {
            question.addAnswer(resultSetAnswer.getString("text"), resultSetAnswer.getBoolean("right_flag"));
        }
        stm2.close();
        stm.close();
        return question;
    }

    @Override
    public void update(Question object) {

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
            Question g = new Question(rs.getString("text"),rs.getString("image_path"));
            g.setId(rs.getInt("id"));
            list.add(g);
        }
        stm.close();
        return list;

    }

    

}
