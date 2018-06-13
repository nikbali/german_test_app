package  model;

import java.util.HashSet;

public class Theme
{
    private int id;
    private String name;
    private String test;
    private HashSet<Question> questions;

    ///////////////////////////////CONSTRUCTORS///////////////////////////////////////////////////////

    public Theme(int id, String name, String test)
    {
        this.id = id;
        this.name = name;
        this.test = test;
    }

    public Theme(String name)
    {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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
                ", test=" + test +
                ", questions=" + questions +
                '}';
    }
}