package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    private int id;
    private String name;
    private List<Theme> thematics = new ArrayList<Theme>();

        /////////////Constructor/////////////////////////
    public Test( int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addThema(Theme thema)
    {
        thematics.add(thema);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thematics=" + thematics +
                '}';
    }
    /////Переопределил для добавления в hashset тематик///////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        return name.equals(test.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}


