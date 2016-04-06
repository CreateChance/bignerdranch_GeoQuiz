package com.example.geoquiz;

/**
 * Created by baniel on 4/6/16.
 */
public class Question {

    private int mQuestionId = -1;

    private boolean mAnswer = false;

    public Question(int id, boolean answer) {
        this.mQuestionId = id;
        this.mAnswer = answer;
    }

    public int getmQuestionId() {
        return mQuestionId;
    }

    public void setmQuestionId(int mQuestionId) {
        this.mQuestionId = mQuestionId;
    }

    public boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestionId=" + mQuestionId +
                ", mAnswer=" + mAnswer +
                '}';
    }
}
