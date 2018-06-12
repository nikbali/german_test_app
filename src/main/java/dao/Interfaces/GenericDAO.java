package dao.Interfaces;

import Constant.TypeQueryQuestion;
import model.Question;
import java.sql.SQLException;
import java.util.List;

/**
 * Единый интерфейс для работы с состоянием объектов
 * @author Nikita Balily
 * @param <T> тип объекта
 */
public interface GenericDAO <T>  {

    /** Создает новую запись и соответствующий ей объект
     * @return  int значение Id созданной записи
     **/
     int create(T object) throws SQLException;

    /** @return  объект  соответствующий записи с первичным ключом key или null */
     T getByPK(int pk) throws SQLException;

    /** Изменяет объект в базе данных */
    void update(T object) throws SQLException;

    /** Удаляет запись  из базы данных по PK */
    boolean delete(int pk) throws SQLException;


    /**
     * Метод возвращает список всех объектов из БД
     * @return List объектов типа T
     */
    List<T> getAll() throws SQLException;
}
