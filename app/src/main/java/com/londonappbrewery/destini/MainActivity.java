package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {


    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;
    // TODO: Declare as variaveis aqui:

    Story mT1 = new Story(R.string.T1_Story);
    Story mT2 = new Story(R.string.T2_Story);
    Story mT3 = new Story(R.string.T3_Story);
    Story mT4 = new Story(R.string.T4_End);
    Story mT5 = new Story(R.string.T5_End);
    Story mT6 = new Story(R.string.T6_End);

    Answer mA1_T1 = new Answer(R.string.T1_Ans1);
    Answer mA2_T1 = new Answer(R.string.T1_Ans2);

    Answer mA1_T2 = new Answer(R.string.T2_Ans1);
    Answer mA2_T2 = new Answer(R.string.T2_Ans2);

    Answer mA1_T3 = new Answer(R.string.T3_Ans1);
    Answer mA2_T3 = new Answer(R.string.T3_Ans2);

    //indice corrente da historia
    private Story mStorySelected;

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            mStorySelected = (Story) savedInstanceState.getSerializable("StoryKey");
            mStoryTextView.setText(mStorySelected.getStoryID());
            mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
            mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Faça o link do layout com a activity
        mStoryTextView = findViewById(R.id.storyTextView);
        mAnswerTop = findViewById(R.id.buttonTop);
        mAnswerBottom = findViewById(R.id.buttonBottom);

        //TODO:faça o mapeamento da história
        mT1.setAnswerTop(mA1_T1);
        mT1.setAnswerBottom(mA2_T1);
        mA1_T1.setChildStory(mT3);
        mA2_T1.setChildStory(mT2);

        mT2.setAnswerTop(mA1_T2);
        mT2.setAnswerBottom(mA2_T2);
        mA1_T2.setChildStory(mT3);
        mA2_T2.setChildStory(mT5);

        mT3.setAnswerTop(mA1_T3);
        mT3.setAnswerBottom(mA2_T3);
        mA1_T3.setChildStory(mT6);
        mA2_T3.setChildStory(mT5);

        mStorySelected = mT1;

        mStoryTextView.setText(mStorySelected.getStoryID());
        mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());

        mAnswerTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorySelected = mStorySelected.getAnswerTop().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop()==null){
                    mAnswerTop.setVisibility(v.GONE);
                    mAnswerBottom.setVisibility(v.GONE);
                } else {
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorySelected = mStorySelected.getAnswerBottom().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop()==null){
                    mAnswerTop.setVisibility(v.GONE);
                    mAnswerBottom.setVisibility(v.GONE);
                } else {
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });

        // TODO: Coloque o evento do click do botão, caso precise colocar a visibilidade no botão invisivel utilize a função
        // do botão setVisibility(View.GONE):

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("StoryKey", mStorySelected);
    }



}
