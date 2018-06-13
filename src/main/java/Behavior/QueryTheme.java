package Behavior;

import dao.MySQLThemeDAO;
import model.Theme;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryTheme extends Query<Theme> {

    private final Connection connection;
    private HashMap<String, Theme> thematics = new HashMap<String, Theme>();
    public QueryTheme(Connection connection) {
        this.connection = connection;
    }


    @Override
    protected void getAll() throws SQLException {

    }

    @Override
    protected ArrayList<Theme> filter(List<Theme> objsects) {
        return null;
    }

    @Override
    protected String seralize(List objsects) {
        return null;
    }
}
