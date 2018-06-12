package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dao.Interfaces.GenericDAO;
import dao.JsonUser;
import dao.Factories.MySqlDaoFactory;
import model.Error;
import model.Question;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Сервлет обрабатывает POST запрос по созданию объекта Question
 * @author Nikita Balily
 */
@WebServlet("/create")
@MultipartConfig
public class CreateQuestionServlet extends HttpServlet {
    public static final String SAVE_DIRECTORY = "uploadDir";

@Override
protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    //cоздали объект вопроса и сетим ему поля
    Question current_question = new Question("", "");
    current_question.setTextOfQuestion(httpServletRequest.getParameter("text_question"));
    Part filePart = httpServletRequest.getPart("upload");
    String fileName = getSubmittedFileName(filePart);
    Enumeration<String> params_names = httpServletRequest.getParameterNames();

    String appPath = httpServletRequest.getServletContext().getRealPath("");
    appPath = appPath.replace('\\', '/');

    // путь к директории в файловой системе в которую сохраняем файл
    String fullSavePath = null;
    if (appPath.endsWith("/")) {
        fullSavePath = appPath + SAVE_DIRECTORY;
    } else {
        fullSavePath = appPath + "/" + SAVE_DIRECTORY;
    }
    // создаем директорию
    File fileSaveDir = new File(fullSavePath);
    if (!fileSaveDir.exists()) {
        fileSaveDir.mkdir();
    }

    //проходим циклом по всем параметром и сетим значения в объект current_question
    while (params_names.hasMoreElements())
    {
        String param = params_names.nextElement();
        String[] str = param.split("_");
        if(str[0].equals("name"))
        {
            current_question.addAnswer(httpServletRequest.getParameter(param), false);
        }
        if(str[0].equals("isRight"))
        {
            current_question.getAnswer(Integer.parseInt(str[1])-1).setRight(true);
        }

    }
    //так как возможено что вопрос создается без приложенного файла
    if(fileName != null && fileName.length() > 0)
    {
        String filePath = fullSavePath + File.separator + fileName; //полный путь к файлу
        BufferedInputStream fileContent = new BufferedInputStream(filePart.getInputStream());
        FileOutputStream out = new FileOutputStream(filePath);
        while (fileContent.available() > 0) {
            int data = fileContent.read();
            out.write(data);
        }
        current_question.setStringOfImageForQuestion(fileName);
        out.flush();
        out.close();
        fileContent.close();


    }
    try
    {
        MySqlDaoFactory dao = MySqlDaoFactory.getInstance();
        GenericDAO<Question> question_dao = dao.getQuestionDAO();
        question_dao.create(current_question);
        httpServletResponse.getWriter().write("<p> Success! Created question!</p>" +
                current_question.toString() + "<br>" + fullSavePath);
    }
    catch (ClassNotFoundException ex)
    {
        System.out.println(ex.getMessage());
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        httpServletResponse.getWriter().write("<p> Error:</p>" + ex.getMessage());
    }
    catch (SQLException ex)
    {
        System.out.println(ex.getMessage());
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        httpServletResponse.getWriter().write("<p> Error:</p>" + ex.getMessage());
    }
}


    /**
    * Метод скопипастил, возвращает имя файла по его Part
     */
    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
