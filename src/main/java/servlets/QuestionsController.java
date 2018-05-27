package servlets;

import com.google.gson.Gson;

import dao.DAOFactory;
import dao.GenericDAO;
import dao.MySqlDaoFactory;
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

@WebServlet("/queryquestion")
public class QuestionsController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        try
        {
            int id =Integer.parseInt(req.getParameter("id"));
            MySqlDaoFactory dao = MySqlDaoFactory.getInstance();
            GenericDAO<Question> question_dao = dao.getQuestionDAO();
            Question question = question_dao.getByPK(id);
            Gson gson = new Gson();
            String stringJson = gson.toJson(question);
            System.out.println(stringJson);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(res.getOutputStream()));
            writer.write(stringJson);
            writer.flush();
            writer.close();
        }
        catch (SQLException sql_ex)
        {
           sql_ex.getMessage();

        }
        catch (NumberFormatException ex)
        {
            ex.getMessage();
        }

    }
}
