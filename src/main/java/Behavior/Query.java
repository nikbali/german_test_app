package Behavior;


import java.sql.SQLException;
import java.util.List;

/**
 * Абстрактный класс Query, задает алгоритм действий необходимых для поиска в БД и создания объектов сущностей
 * @author Nikita Balily
 * */
public abstract class Query<T> {

    public final String getResultToJson()
    {
       String res = "";
       return res;
    }

    protected abstract void getAll() throws SQLException;
    protected abstract List<T> filter(List<T> objsects);
    protected abstract String seralize(List<T> objsects);


}
