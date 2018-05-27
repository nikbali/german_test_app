package test;

import dao.GenericDAO;
import dao.MySqlDaoFactory;
import model.Question;
import org.junit.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

/**
 * Этот класс сделал чисто для себя, чтобы методы тестировать,
 * так что не надо выебываться на меня что тесты написаны как у обезьяны
 */

public class MySqlQuestionDaoTest {

    MySqlDaoFactory dao;
    GenericDAO<Question> question_dao;
    static int num;

    @Before
    public void gen() throws SQLException {
         dao = MySqlDaoFactory.getInstance();
         question_dao = dao.getQuestionDAO();
         dao.getConnection().setAutoCommit(false);
    }
    @Test
    public void create() throws Exception {
        Question test_question = new Question("Test"+num, "Image"+num+".png" );
       test_question.addAnswer("ans"+ (++num) , false);
        test_question.addAnswer("ans"+ (++num) , true);
        test_question.addAnswer("ans"+ (++num) , true);
        test_question.addAnswer("ans"+ (++num) , false);

        System.out.println("Создан вопрос с Id: " + question_dao.create(test_question));

    }
    @Test
    public  void delete() throws Exception {
        assertEquals(true, question_dao.delete(8));
        assertEquals(false, question_dao.delete(766));

    }
    @Test
    public void getByPK() throws Exception {
        System.out.println(question_dao.getByPK(1));
    }
    @Test
    public void getAll() throws Exception {
        List<Question> questions = question_dao.getAll();
        System.out.println(questions);
    }
    @After
    public void rollBack() throws Exception {
        dao.getConnection().rollback();
    }

}