package servlets;

import com.google.gson.Gson;
import dao.GenericDAO;
import dao.MySqlDaoFactory;
import model.Error;
import model.Question;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/queryquestion")
public class QueryQuestionController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        Gson gson = new Gson();
        String json = "";
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(res.getOutputStream()));
        try
        {
            MySqlDaoFactory dao = MySqlDaoFactory.getInstance();
            GenericDAO<Question> question_dao = dao.getQuestionDAO();
            int id =Integer.parseInt(req.getParameter("id"));
            //если параметр id равен -1 возращаем список всех вопросов
            if(id == -1)
            {
                List<Question> questions = question_dao.getAll();
                json = gson.toJson(questions);
            }
            //иначе возвращаем вопрос по id
            else
            {
                Question question = question_dao.getByPK(id);
                json = gson.toJson(question);
            }

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
        finally {
            writer.write(json);
            writer.flush();
            writer.close();
        }

    }
}
