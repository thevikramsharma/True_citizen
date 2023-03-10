package com.example.truecitizen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private int questionno=0;
    private Question[] questionsbank =new Question[]{
            new Question(R.string.question_amendments,false),
            new Question(R.string.question_constitution,true),
            new Question(R.string.question_declaration,true),
            new Question(R.string.question_independence_rights,true),
            new Question(R.string.question_religion,true),
            new Question(R.string.question_government,false),
            new Question(R.string.question_government_feds,false),
            new Question(R.string.question_government_senators,true),

        };
            private Button True;
            private Button False;
            private Button next;
            private Button Prev;
            private TextView showQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        True=findViewById(R.id.Tbutton);
        False=findViewById(R.id.Fbutton);
        next=findViewById(R.id.next);
        Prev=findViewById(R.id.pre);
        showQuest=findViewById(R.id.question);
        showQuest.setText(questionsbank[0].getAnswerResId());
        True.setOnClickListener(view -> {
            checkAnswer(true);
        });
        False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            questionno=(questionno+1)%questionsbank.length;
            changequest();
            Prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(questionno>0){
                        questionno=(questionno-1)%questionsbank.length;
                        changequest();
                    }
                }
            });
            }
            private void changequest() {
                showQuest.setText(questionsbank[questionno].getAnswerResId());
            }
        });

    }

    private void checkAnswer( boolean userChooseCorrect) {
        boolean answerIsCorrect = questionsbank[questionno].isAnswerTrue();
        int messId;
        if(answerIsCorrect== userChooseCorrect){
            messId=R.string.correct_answer;
        }
        else {
            messId=R.string.wrong_answer;
        }
        Snackbar.make(next,messId,Snackbar.LENGTH_SHORT).show();
    }
}