package dao;

import dao.Interfaces.GenericDAO;
import model.Question;
import model.Theme;
import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;



public class MySqlQuestionDao implements GenericDAO<Question> {
    private final Connection connection;
    private static HashMap<String,Theme> cash_thematics = new HashMap<String, Theme>();
    public MySqlQuestionDao(Connection connection)
    {
        this.connection = connection;
        fillMapWithTheme();
    }


    @Override
    public int create(Question question) throws SQLException {

        String sql_answer_create = "INSERT into Answer(text, question_id, right_flag) VALUES(?,?,?)";
        String sql_create_question = "INSERT into Question(text, image_path, theme_id) value(?,?,?)";
        String query_last = "SELECT * FROM Question \n" +
                "ORDER BY id desc limit 1;";

        /**
         * Проверка, есть ли тема в мапе, если нет, создаем новую тематику
         */
        String name_theme = question.getThema();
        int theme_id;
        if(cash_thematics.containsKey(name_theme)) theme_id = cash_thematics.get(name_theme).getId();
        else
        {
            Theme t = new Theme(name_theme);
            theme_id = new MySQLThemeDAO(connection).create(t);
            cash_thematics.put(name_theme, t);
        }

        PreparedStatement ps;
        ps = connection.prepareStatement(sql_create_question);
        ps.setString(1, question.getTextOfQuestion());
        ps.setString(2, question.getStringOfImageForQuestion());
        ps.setInt(3, theme_id);
        ps.executeUpdate();
        PreparedStatement stm2 = connection.prepareStatement(query_last);
        ResultSet rs = stm2.executeQuery();
        rs.next();
        int id = rs.getInt("id");
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

        //добавление темы к вопросу
        int thema_id = rs.getInt("theme_id");
        Theme theme = new MySQLThemeDAO(connection).getByPK(thema_id);

        Question question = new Question(rs.getString("text"),rs.getString("image_path"), theme.getName());
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
            //добавление темы к вопросу
            int thema_id = rs.getInt("theme_id");
            Theme theme = new MySQLThemeDAO(connection).getByPK(thema_id);
            Question question = new Question(rs.getString("text"),rs.getString("image_path"), theme.getName());
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



    /**
     * Метод возвращает список из N случайных вопросов из БД
     * @param N количество вопросов
     * @return ArrayList вопросов
     */
    public ArrayList<Question> getRandom(int N) throws SQLException
    {
        String sql = "SELECT * FROM Question ORDER BY RAND() LIMIT ?;";
        String sql_answers = "SELECT * FROM Answer WHERE question_id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, N);
        ResultSet rs = stm.executeQuery();
        ArrayList<Question> list = new ArrayList<Question>();
        while (rs.next()) {
            //добавление темы к вопросу
            int thema_id = rs.getInt("theme_id");
            Theme theme = new MySQLThemeDAO(connection).getByPK(thema_id);
            Question question = new Question(rs.getString("text"),rs.getString("image_path"), theme.getName());
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


    /**
     * Методы возвращает список вопросов указанной тематики
     * @param name наименование тематики
     * @param N кол-во вопросов(случайное)
     * @return ArrayList вопросов
     */
    public ArrayList<Question> getQuestionByTheme(String name, int N) throws SQLException
    {
        //два различных варианта запроса для всех или для N записей
        String sql_all = "select * \n" +
                "from Question q inner join Theme t on q.theme_id = t.id\n" +
                "where t.name = ?;";
        String sql_rand = "select * \n" +
                "from Question q inner join Theme t on q.theme_id = t.id\n" +
                "where t.name = ? ORDER BY RAND() LIMIT ?;";

        String sql_answers = "SELECT * FROM Answer WHERE question_id = ?;";
        PreparedStatement stm = connection.prepareStatement(N>0?sql_rand:sql_all);
        stm.setString(1, name);
        if(N>0)stm.setInt(2, N);//костль небольшой
        ResultSet rs = stm.executeQuery();
        ArrayList<Question> list = new ArrayList<Question>();
        while (rs.next()) {
            Question question = new Question(rs.getString("text"),rs.getString("image_path"), name);
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
    public ArrayList<Question> getQuestionByTheme(String name) throws SQLException
    {
        return getQuestionByTheme(name, -1);
    }


    /**
     * Метод заполняет кэш с темами
     */
    private  void fillMapWithTheme()
    {
        try {
            ArrayList<Theme> listTheme = new MySQLThemeDAO(connection).getAll();
            for (Theme t : listTheme) {
                cash_thematics.put(t.getName(), t);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Мапа с кэшем Тем не заполнена");
        }
    }

}
