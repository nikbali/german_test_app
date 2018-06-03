package Constant;

/**
 * Enum для хранения констант в дальнейшем для рефакторинга кода понадобится
 */
public enum TypeQueryQuestion {

    GET_ALL("SELECT * FROM Question;"),
    GET_RANDOM("SELECT * FROM Question ORDER BY RAND() LIMIT ?;");

    private String value;
    TypeQueryQuestion(String value)
    {
        this.value = value;
    }
    public String getQuery()
    {
        return value;
    }
}
