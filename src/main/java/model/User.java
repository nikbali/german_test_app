package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User  {
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private String date_registration;


    public User(String login, String first_name, String last_name, String password)
    {
        this.login = login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm") ;
        this.date_registration = dateFormat.format(new Date());

    }

    public String getLogin() {
        return login;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public String getDate_registration() {
        return date_registration;
    }



}
