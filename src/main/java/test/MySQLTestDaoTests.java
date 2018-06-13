package test;
import dao.Factories.MySqlDaoFactory;
import dao.MySQLThemeDAO;
import dao.MySqlTestDAO;
import model.Question;
import model.Theme;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
public class MySQLTestDaoTests {
    MySqlDaoFactory dao;
    MySqlTestDAO test_dao;
    MySQLThemeDAO tematics;
    @Before
    public void gen() throws Exception
    {
        dao = MySqlDaoFactory.getInstance();
      //  test_dao = (MySqlTestDAO) dao.getTestDAO();
        tematics = (MySQLThemeDAO) dao.getThemeDAO();
        dao.getConnection().setAutoCommit(false);
    }

    @Test
    public void getTests() throws Exception
    {
        List<model.Test> tests = test_dao.getAll();
        System.out.println(tests);
    }
    @Test
    public void getTematics() throws Exception
    {
        List<Theme> tems = tematics.getAll();
        System.out.println(tems);
    }
}
