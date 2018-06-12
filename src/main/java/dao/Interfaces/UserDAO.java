package dao.Interfaces;

import model.User;

import java.util.ArrayList;

/**
 * Интерфейс для работы с состоянием объектов типа User
 * @author Nikita Balilyy
 */
public interface UserDAO {
    public boolean addUser(User user);
    public boolean checkUser(String login);
    public boolean login(String login, String password);
    public User getUser(String login);
    public ArrayList<User> getAll() throws Exception;

}
