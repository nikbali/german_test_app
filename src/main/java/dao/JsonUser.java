package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonUser implements UserDAO {
    private final static String USERS_FILE = "src/main/resurces/users.json";

    @Override
    public boolean addUser(User user) {
        ArrayList<User> users = null;
        JsonWriter writer = null;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        if(this.hasFile()) {
            try
            {
                users = this.getAll();
                if(users == null)return false;
                users.add(user);
                writer = new JsonWriter(new FileWriter(USERS_FILE));
                gson.toJson(users, type, writer);

                writer.flush();
                writer.close();
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        else
        {
            try {
                users = new ArrayList<User>();
                users.add(user);
                writer = new JsonWriter(new FileWriter(USERS_FILE));
                gson.toJson(users, type, writer);

                writer.flush();
                writer.close();
                return true;
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
                return false;
            }

        }

    }

    @Override
    public boolean checkUser(String login) {
        return false;
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public User getUser(String login) {
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        if (this.hasFile())
        {
            ArrayList<User> users = null;
            try
            {
                JsonReader reader = new JsonReader(new FileReader(USERS_FILE));
                Type type = new TypeToken<ArrayList<User>>() {}.getType();
                users = new Gson().fromJson(reader, type);
                reader.close();
                return users;
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
                return null;
            }
        }
        else return null;
    }

    //json файл существует
    private boolean hasFile()
    {
        Gson gson = new Gson();
        ArrayList<User> users = null;
        try
        {
            JsonReader reader = new JsonReader(new FileReader(USERS_FILE));
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            users = new Gson().fromJson(reader,type);
            if(users.size() > 0 ) return true;
            else return false;
        }
        catch (NullPointerException ex){
            return false;
        }
        catch (FileNotFoundException fex)
        {
            return false;
        }
    }
}
