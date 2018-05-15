package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnect {

    public static Connection getConnecttion() {
        String userName = "gb_db_nikbali";
        String password = "3c52724479a";
        String connectionUrl = "jdbc:mysql://mysql99.1gb.ru:3306/gb_db_nikbali";
        Connection cons = null;
        try
        {
            cons = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("Все гуд");
            //learning to work with database
            Statement statement = cons.createStatement();
            statement.executeUpdate("drop table User;");
            statement.executeUpdate("create table User (ROW_ID int NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME varchar(30) NOT NULL);");
            statement.executeUpdate("insert into User(NAME) values ('Никита');");
            ResultSet result = statement.executeQuery("select * from User;");
            while(result.next())
            {
                System.out.println(result.getString("ROW_ID"));
                System.out.println(result.getString("NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }

    public static void main(String[] args) {
        getConnecttion();
    }
}
