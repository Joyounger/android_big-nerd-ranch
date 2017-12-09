package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    public final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private boolean mIsCheater;
    private TextView mQuestionTextView;
    public final String KEY_INDEX = "index";
    public final String KEY_SHOWN = "index_cheat";


    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        if (mIsCheater) {
            Toast.makeText(QuizActivity.this, "Cheating is wrong", Toast.LENGTH_SHORT).show();
        } else {
            if (userPressedTrue == mQuestionBank[mCurrentIndex].isTrueQuestion()) {
                Toast.makeText(QuizActivity.this, "corrected!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QuizActivity.this, "incorrected!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater= savedInstanceState.getBoolean(KEY_SHOWN);
        }


        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            }
        );

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            }
        );

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                    mIsCheater = false;
                }
            }
        );

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                    mIsCheater = false;
                }
            }
        );

        mPrevButton = (Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                   if (mCurrentIndex < 0) {
                       mCurrentIndex = mQuestionBank.length - 1;
                   }
                   updateQuestion();
                   mIsCheater = false;
               }
           }
        );

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                   //start activity
                   Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                   boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                   i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                   //startActivity(i);
                    startActivityForResult(i, 0);
               }
           }
        );

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(KEY_SHOWN, mIsCheater);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }
}
