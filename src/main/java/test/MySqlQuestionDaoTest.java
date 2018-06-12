package test;

import dao.Interfaces.GenericDAO;
import dao.Factories.MySqlDaoFactory;
import dao.MySqlQuestionDao;
import model.Question;
import org.junit.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.Assert.assertEquals;

/**
 * Этот класс сделал чисто для себя, чтобы методы тестировать,
 * так что не надо выебываться на меня что тесты написаны как у обезьяны
 */

public class MySqlQuestionDaoTest {

    MySqlDaoFactory dao;
    MySqlQuestionDao question_dao;
    static int num;

    @Before
    public void gen() throws Exception {
         dao = MySqlDaoFactory.getInstance();
         question_dao = dao.getQuestionDAO();
         dao.getConnection().setAutoCommit(false);
    }
    @Test
    public void create() throws Exception {
        Question test_question = new Question("Auf welcher Straße steht der Reichstag?", "Street.png" );
        test_question.addAnswer("Scheidemannstraße 2" , true);
        test_question.addAnswer("Monckebergstraße 33" , false);
        test_question.addAnswer("Karl Marx Straße 3" , false);
        test_question.addAnswer("Wasserwerkstraße 8" , false);

        System.out.println("Создан вопрос с Id: " + question_dao.create(test_question));

    }
    @Test
    public  void delete() throws Exception {
        assertEquals(true, question_dao.delete(8));
        assertEquals(false, question_dao.delete(766));

    }
    @Test
    public void getByPK() throws Exception {
        System.out.println(question_dao.getByPK(4));
    }
    @Test
    public void getAll() throws Exception {
        List<Question> questions = question_dao.getAll();
        System.out.println(questions);
    }

    @Test
    public void getRandom() throws Exception
    {
      ArrayList<Question> questions = question_dao.getRandom(20);
      for(Question obj : questions)
      {
          System.out.println(obj);
      }
    }
    @After
    public void rollBack() throws Exception {
       dao.getConnection().rollback();
    }


}