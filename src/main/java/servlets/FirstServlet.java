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

@WebServlet("/server")
public class FirstServlet extends HttpServlet {
    private final static String USERS_FILE = "/res/users.json";

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        System.out.println(httpServletRequest.getContextPath() + "/ " );
        ServletContext context = this.getServletContext();




        PrintWriter writer_html = new PrintWriter(new OutputStreamWriter(httpServletResponse.getOutputStream(), "Cp1251"));
        User user0 = new User("qwe@fsef", "niki", "bali", "pass");
        User user1 = new User("qwe@fsef1", "niki1", "bali22", "pass1");
        User user2 = new User("qwe@fsef2", "niki12", "bali222", "pass11");
        User user3 = new User("qwe@fsef3", "niki123", "bali22233", "pass111");
        Gson gson = new Gson();
        InputStream in_stream = getClass().getResourceAsStream("/users.json");
        //JsonReader reader = new JsonReader(new InputStreamReader(in_stream));
        int r  = in_stream.read();
        while(r != -1)
        {
            System.out.println(r);
            r = in_stream.read();
        }

        ArrayList<User> users = new ArrayList<User>();
        users.add(user0);
        users.add(user1);





        writer_html.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <title>Web Page</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Form</h1>\n" +
                "\n" +
                "        <form action=\"server\" method=\"get\">\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        id\n" +
                "                    </td>\n" +
                "                    <td>\n" +
                "                        <input type=\"text\" name=\"id\">\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>name</td>\n" +
                "                    <td>\n" +
                "\t\t\t\t\t\t<input type=\"text\" name=\"name\">\n" +
                "\t\t\t\t\t</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td></td>\n" +
                "                    <td>\n" +
                "\t\t\t\t\t\t<input type=\"submit\" value=\"Send\">\n" +
                "\t\t\t\t\t</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </form>\n" +
                "    </body>\n" +
                "</html>");
        writer_html.flush();
        writer_html.close();
        Enumeration<String> params_names = httpServletRequest.getParameterNames();
        while (params_names.hasMoreElements())
        {
            String param = params_names.nextElement();
            System.out.println(param + " : " + httpServletRequest.getParameter(param));
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
