package model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Question {
    private int id;
    private String textOfQuestion;
    private String linkOfImageForQuestion;
    private String theme;
    private ArrayList<Answer> answers = new ArrayList<Answer>();
    public class Answer {
        private int id;
        private String text;
        private boolean isRight;

        public Answer(String text, boolean right) {
            this.isRight = right;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isRight() {
            return isRight;
        }

        public void setRight(boolean right) {
            isRight = right;
        }

        @Override
        public String toString() {
            return "Answer{" +
                    " text='" + text + '\'' +
                    " isRight= '" + isRight + '\''+
                    '}';
        }
    }

    /**
     ///////////////////////////Конструкторы//////////////////////////////////////////////////////////
     */
    public Question(String textOfQuestion, String stringOfImageForQuestion,  String theme) {

        this.textOfQuestion = textOfQuestion;
        this.linkOfImageForQuestion = stringOfImageForQuestion;
        this.theme = theme;
    }

/**
    ///////////////////////////ГЕТТЕРЫ и СЕТТЕРЫ//////////////////////////////////////////////////////////
 */
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
        return linkOfImageForQuestion;
    }
    public void setStringOfImageForQuestion(String stringOfImageForQuestion) {
        this.linkOfImageForQuestion = stringOfImageForQuestion;
    }
    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public String getThema() {
        return theme;
    }
    public void setThema(String thema) {
        this.theme = thema;
    }

    /**
     * Метод возвращет Answer по его индексу в спаиске
     * @param i номер индекса ответа
     * @return Answer
     */
    public Answer getAnswer(int i)
    {
        return this.answers.get(i);
    }

    /**
     * Три различных методв для добавления ответов
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
    public void addAnswer(String text_answer, boolean right) {
        this.answers.add(new Answer(text_answer, right));
    }
    public void addAnswer(int id, String text_answer, boolean right) {
        Answer answer = new Answer(text_answer, right);
        answer.setId(id);
        this.answers.add(answer);
    }


    @Override
    public String toString() {
        return "Question{ \n" +
                "id='" + (id==0?"Noun":id) + '\'' +
                ",\ntextOfQuestion='" + textOfQuestion + '\'' +
                ",\ntheme='" + theme + '\'' +
                ",\nstringOfImageForQuestion='" + linkOfImageForQuestion + '\'' +
                ",\nanswers=" + answers +
                '}';
    }

}
