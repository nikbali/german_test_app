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
    public int create(Question question) throws SQLException {
        String sql_answer_create = "INSERT into Answer(text, question_id, right_flag) VALUES(?,?,?)";
        String sql_create_question = "INSERT into Question(text, image_path) value(?,?)";
        String query_last = "SELECT * FROM Question \n" +
                "ORDER BY id desc limit 1;";
        int id = -1;
        PreparedStatement ps;

            ps = connection.prepareStatement(sql_create_question);
            ps.setString(1, question.getTextOfQuestion());
            ps.setString(2, question.getStringOfImageForQuestion());
            ps.executeUpdate();
            PreparedStatement stm2 = connection.prepareStatement(query_last);
            ResultSet rs = stm2.executeQuery();
            rs.next();
            id = rs.getInt("id");
            stm2.close();
            if(question.getAnswers().size() != 0)
            {
                PreparedStatement stm3 = connection.prepareStatement(sql_answer_create);
                for(Question.Answer answer : question.getAnswers())
                {
                    stm3.setString(1, answer.getText());
                    stm3.setInt(2, id);
                    stm3.setBoolean(3, answer.isRight());
                    stm3.executeUpdate();
                }
                stm3.close();
            }
            ps.close();

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
    public void update(Question object) throws SQLException{
        //TODO пока реализацию этого метода отложим, требуется уточнение (какие поля обнавлять нужно будет и т.д)
    }

    @Override
    public boolean delete(int pk) throws SQLException {
        String sql_delete_question = " DELETE FROM Question\n" +
                "WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql_delete_question);
            ps.setInt(1, pk);
            int cnt = ps.executeUpdate();
            ps.close();
            return (cnt>0?true:false);
    }

    @Override
    public ArrayList<Question> getAll() throws SQLException {
        String sql = "SELECT * FROM Question;";
        String sql_answers = "SELECT * FROM Answer WHERE question_id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        ArrayList<Question> list = new ArrayList<Question>();
        while (rs.next()) {
            Question question = new Question(rs.getString("text"),rs.getString("image_path"));
            int id = rs.getInt("id");
            question.setId(id);
            PreparedStatement stm2 = connection.prepareStatement(sql_answers);
            stm2.setInt(1, id);
            ResultSet resultSetAnswer = stm2.executeQuery();
            while (resultSetAnswer.next())
            {
                question.addAnswer(resultSetAnswer.getInt("id"), resultSetAnswer.getString("text"), resultSetAnswer.getBoolean("right_flag"));
            }
            stm2.close();
            list.add(question);
        }
        stm.close();
        return list;

    }

    

}
