package test;
import dao.Factories.MySqlDaoFactory;
import dao.MySqlTestDAO;
import model.Question;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
public class MySQLTestDaoTests {
    MySqlDaoFactory dao;
    MySqlTestDAO test_dao;
    @Before
    public void gen() throws Exception
    {
        dao = MySqlDaoFactory.getInstance();
        test_dao = (MySqlTestDAO) dao.getTestDAO();
        dao.getConnection().setAutoCommit(false);
    }

    @Test
    public void test() throws Exception
    {
        List<model.Test> tests = test_dao.getAll();
        System.out.println(tests);
    }
}
