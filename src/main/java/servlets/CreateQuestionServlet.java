package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dao.JsonUser;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Сервлет обрабатывает POST запрос по созданию объекта Question
 * @author Nikita Balily
 */
@WebServlet("/create")
public class CreateQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        System.out.println(httpServletRequest.getContextPath() + "/ " );
        Enumeration<String> params_names = httpServletRequest.getParameterNames();
        while (params_names.hasMoreElements())
        {
            String param = params_names.nextElement();
            System.out.println(param + " : " + httpServletRequest.getParameter(param));
        }
    }
}
