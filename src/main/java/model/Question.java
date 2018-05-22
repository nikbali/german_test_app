package model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Question {
    private int id;
    private String textOfQuestion;
    private String stringOfImageForQuestion;
    private int idRightAnswer;
    private ArrayList<Answer> answers;
    private static int count = 0;

    public Question(int id, String textOfQuestion, String stringOfImageForQuestion) {
        this.id = id;
        this.textOfQuestion = textOfQuestion;
        this.stringOfImageForQuestion = stringOfImageForQuestion;

    }

    @Override
    public String toString() {
        return "Question{ \n" +
                "textOfQuestion='" + textOfQuestion + '\'' +
                ",\nstringOfImageForQuestion='" + stringOfImageForQuestion + '\'' +
                ",\nidRightAnswer=" + idRightAnswer +
                ",\nanswers=" + answers +
                '}';
    }

    public static void main(String[] args) {
        Answer answer0 = new Answer("Programming");
        Answer answer1 = new Answer("Playing a game");
        Answer answer2 = new Answer("Sleeping");
        Answer answer3 = new Answer("Sleeping");
        ArrayList<Answer> arr = new ArrayList<Answer> ();
        arr.add(answer0);
        arr.add(answer1);
        arr.add(answer2);
        arr.add(answer3);
       // Question question = new Question("What is Nikita doing?" , null,arr );
        Gson gson = new Gson();
       // String stringJson = gson.toJson(question);
        //System.out.println(stringJson);
    }
}
