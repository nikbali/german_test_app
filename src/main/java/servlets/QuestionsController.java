package servlets;

import com.google.gson.Gson;

import model.Question;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/givemequestion")
public class QuestionsController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        /*
        Answer answer0 = new Answer("Programming");
        Answer answer1 = new Answer("Playing a game");
        Answer answer2 = new Answer("Sleeping");
        Answer answer3 = new Answer("Sleeping");
        ArrayList<Answer> arr = new ArrayList<Answer> ();
        arr.add(answer0);
        arr.add(answer1);
        arr.add(answer2);
        arr.add(answer3);
        */
        Question question = new Question("What is Nikita doing?" , "d.png" );
        Gson gson = new Gson();
        String stringJson = gson.toJson(question);

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(res.getOutputStream()));
        String id = req.getParameter("id");
        if(id.equals("0"))
        {
            writer.write(stringJson);

        }
        else writer.write("Huec tibe a ne vopros");
        writer.flush();
        writer.close();
    }
}
