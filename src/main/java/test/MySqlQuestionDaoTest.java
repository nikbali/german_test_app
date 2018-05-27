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
    @Before
    public void gen() throws SQLException
    {
         dao = MySqlDaoFactory.getInstance();
         question_dao = dao.getQuestionDAO();

    }
    @Test
    public void create() throws Exception {

    }

    @Test
    public void getByPK() throws Exception {
        System.out.println(question_dao.getByPK(3));
    }

    @Test
    public void getAll() throws Exception {
    }

}