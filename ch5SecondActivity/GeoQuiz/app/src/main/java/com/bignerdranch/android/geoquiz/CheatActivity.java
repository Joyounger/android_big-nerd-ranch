package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by linyang on 2017/8/29.
 */

public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

    private boolean mAnsswerIsTrue;
    private Button mShowAnswer;
    private TextView mAnswerTextView;

    public final String KEY_SHOWN = "index_cheat";
    public boolean mIsCheated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        //setAnswerShownResult(false);
        // mIsCheated = false;
        if (savedInstanceState != null) { //必须放到mIsCheated = false;后面
            mIsCheated = savedInstanceState.getBoolean(KEY_SHOWN);
        }
        setAnswerShownResult(mIsCheated);

        mAnsswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (mAnsswerIsTrue) {
                        mAnswerTextView.setText(R.string.true_button);
                    } else {
                        mAnswerTextView.setText(R.string.false_button);
                    }
                   //setAnswerShownResult(true);
                   mIsCheated = true;
                   setAnswerShownResult(mIsCheated);
               }
           }
        );
    }

    private void setAnswerShownResult(boolean isAnswerShowm) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShowm);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_SHOWN, mIsCheated);

    }
}
