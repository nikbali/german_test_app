package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import dao.Interfaces.GenericDAO;
import model.Question;
import model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JSONQuestionDao implements GenericDAO<Question> {

    private final static String USERS_FILE = "D:\\questions.json";
    private static JSONQuestionDao instance;
    private JSONQuestionDao(){}
    public static JSONQuestionDao getInstance()
    {
        if (instance == null) {
            instance = new JSONQuestionDao();
        }
        return instance;
    }
    @Override
    public int create(Question object) throws SQLException {
        return 0;
    }

    @Override
    public Question getByPK(int pk) throws SQLException {
        return null;
    }

    @Override
    public void update(Question object) throws SQLException {

    }

    @Override
    public boolean delete(int pk) throws SQLException {
        return false;
    }

    @Override
    public List<Question> getAll() throws SQLException {
        if (this.hasFile())
        {
            ArrayList<Question> questions = null;
            try
            {
                JsonReader reader = new JsonReader(new FileReader(USERS_FILE));
                Type type = new TypeToken<ArrayList<Question>>() {}.getType();
                questions = new Gson().fromJson(reader, type);
                reader.close();
                return questions;
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
                return null;
            }
        }
        else return null;
    }

    //json файл существует
    public boolean hasFile()
    {
        Gson gson = new Gson();
        ArrayList<Question> question = null;
        try
        {
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(USERS_FILE), "UTF-8" ));
            Type type = new TypeToken<ArrayList<Question>>(){}.getType();
            question = new Gson().fromJson(reader,type);
            if(question.size() > 0 ) return true;
            else return false;
        }
        catch (NullPointerException ex){
            return false;
        }
        catch (FileNotFoundException fex)
        {
            return false;
        }
        catch (UnsupportedEncodingException ex)
        {
            return false;
        }
    }
}
