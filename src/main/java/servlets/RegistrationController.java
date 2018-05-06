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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private final static String USERS_FILE = "src/main/resurces/users.txt";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("click") == null)
        {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/registration.jsp");
            requestDispatcher.forward(request, response);
        }
        else
        {
            String login = request.getParameter("email");
            String password = request.getParameter("password");
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");

            User new_user = new User(login, first_name, last_name, password);
            System.out.println(new_user.getLogin() + " создан");
            JsonUser jsonUser = new JsonUser();
            boolean success = jsonUser.addUser(new_user);
            if(success) {
                request.setAttribute("user", new_user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/succes_registration.jsp");
                requestDispatcher.forward(request, response);
            }
            else
                {
                    response.getWriter().write("<p> Wrong Params. Try again... </p>" +
                            "<p><a href=\"registration\">Login</a></p>");
                }
        }

    }
}
