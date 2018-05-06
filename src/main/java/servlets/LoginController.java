package servlets;

import dao.JsonUser;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginController extends HttpServlet{
    private final static String USERS_FILE = "src/main/resurces/users.txt";
    private JsonUser jsonUser = new JsonUser();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("click") == null)
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/login.jsp");
            requestDispatcher.forward(request, response);
        }
        else
        {
            try {

                String login = request.getParameter("login");
                String password = request.getParameter("password");

                boolean success = jsonUser.login(login, password);
                if(success) {
                    User new_user = jsonUser.getUser(login);
                    request.setAttribute("user", new_user);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/succes_registration.jsp");
                    requestDispatcher.forward(request, response);
                }
                else throw new Exception();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                response.getWriter().write("<p> Wrong Params. Try again... </p>" +
                "<p><a href=\"login\">Login</a></p>");
            }

        }

    }
}
