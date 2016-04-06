package com.example.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    private static final String KEY_INDEX = "KEY_INDEX";

    // UI views
    private TextView mQuestionView = null;
    private Button mTrueBtn = null;
    private Button mFalseBtn = null;
    private Button mPrevBtn = null;
    private Button mNextBtn = null;

    // all questions
    private ArrayList<Question> mQuestionList = new ArrayList<>();
    // current question index.
    private int mCurrentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentQuestion = savedInstanceState.getInt(KEY_INDEX);
        }

        mQuestionView = (TextView) this.findViewById(R.id.questionView);
        mTrueBtn = (Button) this.findViewById(R.id.trueBtn);
        mFalseBtn = (Button) this.findViewById(R.id.falseBtn);
        mPrevBtn = (Button) this.findViewById(R.id.prevBtn);
        mNextBtn = (Button) this.findViewById(R.id.nextBtn);

        mQuestionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click here to next question.
                mCurrentQuestion = (mCurrentQuestion + 1) % mQuestionList.size();
                updateQuestion(mQuestionList.get(mCurrentQuestion));
            }
        });

        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click here to check if answer true is right and info user.
                infoUser(checkAnswer(mQuestionList.get(mCurrentQuestion), true));
            }
        });

        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click here to check if answer false is right and info user.
                infoUser(checkAnswer(mQuestionList.get(mCurrentQuestion), false));
            }
        });

        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click here to goto previous question.
                mCurrentQuestion =
                        (mCurrentQuestion + mQuestionList.size() - 1) % mQuestionList.size();
                updateQuestion(mQuestionList.get(mCurrentQuestion));
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click here to goto next question.
                mCurrentQuestion = (mCurrentQuestion + 1) % mQuestionList.size();
                updateQuestion(mQuestionList.get(mCurrentQuestion));
            }
        });

        // init question list.
        initQuestionList();

        // show the first question.
        updateQuestion(mQuestionList.get(mCurrentQuestion));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_INDEX, mCurrentQuestion);
    }

    private void initQuestionList() {
        mQuestionList.add(new Question(R.string.question_oceans, true));
        mQuestionList.add(new Question(R.string.question_mideast, false));
        mQuestionList.add(new Question(R.string.question_africa, false));
        mQuestionList.add(new Question(R.string.question_americas, true));
        mQuestionList.add(new Question(R.string.question_asia, true));
    }

    private void updateQuestion(Question question) {
        mQuestionView.setText(question.getmQuestionId());
    }

    private boolean checkAnswer(Question question, boolean answer) {
        return (answer == question.getAnswer());
    }

    private void infoUser(boolean result) {
        if (result) {
            Toast.makeText(QuizActivity.this, R.string.answer_correct, Toast.LENGTH_SHORT).
                    show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.answer_incorrect, Toast.LENGTH_SHORT).
                    show();
        }
    }
}
