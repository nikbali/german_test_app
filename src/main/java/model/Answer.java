package model;

public class Answer {
    private int id;
    private String text;
    private static int count = 0;

    public Answer(String text) {
        this.id = count++;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static int getCount() {
        return count;
    }

}
