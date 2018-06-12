package  model;

import java.util.HashSet;
import java.util.Set;

public class Theme
{
    private int id;
    private String name;
    private Test test;
    private HashSet<Question> questions;

    ///////////////////////////////CONSTRUCTORS///////////////////////////////////////////////////////

    public Theme(int id, String name, Test test)
    {
        this.id = id;
        this.test = test;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theme theme = (Theme) o;

        if (id != theme.id) return false;
        if (!name.equals(theme.name)) return false;
        return test.equals(theme.test);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", test=" + test.getName() +
                ", questions=" + questions +
                '}';
    }
}