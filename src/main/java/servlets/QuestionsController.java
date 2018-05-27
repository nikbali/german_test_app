package servlets;

import com.google.gson.Gson;

import com.google.gson.JsonElement;
import dao.DAOFactory;
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

@WebServlet("/queryquestion")
public class QuestionsController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        Gson gson = new Gson();
        String json = "";
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(res.getOutputStream()));
        try
        {
            int id =Integer.parseInt(req.getParameter("id"));
            MySqlDaoFactory dao = MySqlDaoFactory.getInstance();
            GenericDAO<Question> question_dao = dao.getQuestionDAO();
            Question question = question_dao.getByPK(id);
            json = gson.toJson(question);
            System.out.println("Succes Query: "+ json);

        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            json = gson.toJson(new Error("The resource you requested could not be found.", false));
        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            json = gson.toJson(new Error("Incorrect request parameters.", false));
        }
        finally {
            writer.write(json);
            writer.flush();
            writer.close();
        }

    }
}
