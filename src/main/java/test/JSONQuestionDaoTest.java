package test;

import dao.Interfaces.GenericDAO;
import dao.JSONQuestionDao;
import dao.Factories.MySqlDaoFactory;
import model.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JSONQuestionDaoTest {

    GenericDAO<Question> question_dao;
    MySqlDaoFactory dao;
    GenericDAO<Question> question_dao_db;

    @Test
    public void createQuestionFromJson() throws Exception {
        question_dao = JSONQuestionDao.getInstance();
        JSONQuestionDao js = (JSONQuestionDao) question_dao;
        System.out.println(js.hasFile());
        ArrayList<Question> questions = (ArrayList<Question>) question_dao.getAll();
        dao = MySqlDaoFactory.getInstance();
        question_dao_db = dao.getQuestionDAO();
        for(Question q : questions){
                question_dao_db.create(q);
        }

    }


}