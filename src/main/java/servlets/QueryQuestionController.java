package servlets;

import com.google.gson.Gson;
import dao.Interfaces.GenericDAO;
import dao.Factories.MySqlDaoFactory;
import dao.MySqlQuestionDao;
import model.Error;
import model.Question;
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
import java.util.List;


@WebServlet("/queryquestion")
public class QueryQuestionController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        Gson gson = new Gson();
        String json = "";
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), StandardCharsets.UTF_8));

        try
        {
            MySqlDaoFactory dao = MySqlDaoFactory.getInstance();
            MySqlQuestionDao question_dao = dao.getQuestionDAO();
            String id_param = req.getParameter("id");
            String rand_param = req.getParameter("rand");
            String theme = req.getParameter("theme");

///////////////////////////////ДИСПАТЧЕР РАЗЛИЧНЫХ ПАРАМЕТРОВ//////////////////////////////////////////////////////

            ///поиск по параметру id
            if(id_param != null && rand_param == null && theme == null)
            {
                int id = Integer.parseInt(id_param);
                //если параметр id равен -1 возращаем список всех вопросов
                if (id == -1) {
                    List<Question> questions = question_dao.getAll();
                    json = gson.toJson(questions);
                }
                //иначе возвращаем вопрос по id
                else {
                    Question question = question_dao.getByPK(id);
                    json = gson.toJson(question);
                }
            }
            //поиск по наименованию темы
            else if(id_param == null && theme != null)
            {
                //тема и рандом
                if(rand_param != null)
                {
                    int rand = Integer.parseInt(rand_param);
                    List<Question> questions = question_dao.getQuestionByTheme(theme,rand);
                    json = gson.toJson(questions);
                }
                //просто тема
                else
                {
                    List<Question> questions = question_dao.getQuestionByTheme(theme, -1);
                    json = gson.toJson(questions);
                }
            }

            //поиск вопросов только по параметру rand
            else if (id_param == null && rand_param != null && theme == null)
            {
                int rand = Integer.parseInt(rand_param);
                List<Question> questions = question_dao.getRandom(rand);
                json = gson.toJson(questions);
            }
            else throw new NumberFormatException("You can not transfer this combination of parametrs!");



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
