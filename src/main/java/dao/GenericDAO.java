package dao;

import model.Question;
import java.sql.SQLException;
import java.util.List;

/**
 * Единый интерфейс для работы с состоянием объектов
 * @author Nikita Balily
 * @param <T> тип объекта
 */
public interface GenericDAO <T> {

    /** Создает новую запись и соответствующий ей объект
     * @return  int значение Id созданной записи */
     int create(T object);

    /** @return  объект  соответствующий записи с первичным ключом key или null */
     T getByPK(int pk) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    void update(T object);

    /** Удаляет запись об объекте из базы данных */
    void delete(T object);

    /** @return  List объектов соответствующих всем записям в базе данных */
    List<T> getAll() throws SQLException;
}
