package model;

import java.util.List;
import java.util.Set;

public class Test {
    private int id;
    private String name;
    private Set<Theme> thematics;

    /////////////Constructor/////////////////////////
    public Test(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thematics=" + thematics +
                '}';
    }
}


