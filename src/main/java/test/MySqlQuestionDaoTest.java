package test;
import dao.DAOFactory;
import dao.GenericDAO;
import dao.MySqlDaoFactory;
import model.Question;
import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class MySqlQuestionDaoTest {

    MySqlDaoFactory dao;
    GenericDAO<Question> question_dao;
    static int num;
    @Before
    public void gen() throws SQLException
    {
         dao = MySqlDaoFactory.getInstance();
         question_dao = dao.getQuestionDAO();
         num++;

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
    public void getByPK() throws Exception {
        System.out.println(question_dao.getByPK(1));
    }

    @Test
    public void getAll() throws Exception {
    }

}