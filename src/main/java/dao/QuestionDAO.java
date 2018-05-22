package dao;

import model.Question;
import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {

    /** Создает новую запись и соответствующий ей объект */
     Question create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
     Question read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    void update(Question question);

    /** Удаляет запись об объекте из базы данных */
    void delete(Question question);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<Question> getAll() throws SQLException;
}
