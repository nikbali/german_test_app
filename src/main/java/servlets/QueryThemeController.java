package servlets;

import com.google.gson.Gson;
import dao.Factories.MySqlDaoFactory;
import dao.MySQLThemeDAO;
import dao.MySqlQuestionDao;
import dao.MySqlTestDAO;
import model.Error;
import model.Test;
import model.Theme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/querytheme")
public class QueryThemeController  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws  IOException {

        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        Gson gson = new Gson();
        String json = "";

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), StandardCharsets.UTF_8));

        try
        {
            MySqlDaoFactory dao = MySqlDaoFactory.getInstance();
            MySqlTestDAO test_dao = (MySqlTestDAO) dao.getTestDAO();
            String id_param = req.getParameter("test_id");
            Test testWithTematics;
            testWithTematics = test_dao.getByPK(Integer.parseInt(id_param));
            json = gson.toJson(testWithTematics);

        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json = gson.toJson(new Error("Driver JDBC not found.", false, ex.getMessage()));
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json = gson.toJson(new Error("The resource you requested could not be found.", false, ex.getMessage()));

        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json = gson.toJson(new Error("Incorrect request parameters.", false, ex.getMessage()));
        }
        finally{
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
