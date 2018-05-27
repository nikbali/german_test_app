package model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Question {
    private int id;
    private String textOfQuestion;
    private String stringOfImageForQuestion;
    private ArrayList<Answer> answers = new ArrayList<Answer>();

    public class Answer {
        private int id;
        private String text;
        private boolean isRight;

        public Answer(String text, boolean right) {
            this.isRight = right;
            this.text = text;
        }
        @Override
        public String toString() {
            return "Answer{" +
                    " text='" + text + '\'' +
                    " isRight= '" + isRight + '\''+
                    '}';
        }
    }


    public Question(String textOfQuestion, String stringOfImageForQuestion) {

        this.textOfQuestion = textOfQuestion;
        this.stringOfImageForQuestion = stringOfImageForQuestion;

    }

    public String getTextOfQuestion() {
        return textOfQuestion;
    }

    public void setTextOfQuestion(String textOfQuestion) {
        this.textOfQuestion = textOfQuestion;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getStringOfImageForQuestion() {
        return stringOfImageForQuestion;
    }

    public void setStringOfImageForQuestion(String stringOfImageForQuestion) {
        this.stringOfImageForQuestion = stringOfImageForQuestion;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addAnswer(String text_answer, boolean right) {
        this.answers.add(new Answer(text_answer, right));
    }

    @Override
    public String toString() {
        return "Question{ \n" +
                "textOfQuestion='" + textOfQuestion + '\'' +
                ",\nstringOfImageForQuestion='" + stringOfImageForQuestion + '\'' +
                ",\nanswers=" + answers +
                '}';
    }

}
