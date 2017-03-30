package com.example.noahglaser.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Random random = new Random();

    TextView problemText;

    TextView recordText;

    TextView statusText;

    TextView timeKeeper;

    Button answer1;

    Button answer2;

    Button answer3;

    Button answer4;

    Button playAgain;

    Button btns[] = new Button[4];

    int correctBtn;

    int tries = 0;

    int wins = 0;

    int time = 30;

    boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        problemText = (TextView)findViewById(R.id.problemTextView);
        statusText = (TextView)findViewById(R.id.gotItRightTextView);
        timeKeeper = (TextView)findViewById(R.id.timeKeeper);

        answer1 = (Button)findViewById(R.id.answer1);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        answer4 = (Button)findViewById(R.id.answer4);
        playAgain = (Button)findViewById(R.id.restartBtn);

        btns[0] = answer1;
        btns[1] = answer2;
        btns[2] = answer3;
        btns[3] = answer4;

        recordText = (TextView)findViewById(R.id.recordText);
        startGame();


    }

    public void startGame()
    {
        playing = true;
        createRandomProblem();
        wins = 0;
        tries = 0;
        setRecord();

        new CountDownTimer(time * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = ((int)millisUntilFinished / 1000);
                timeKeeper.setText(Integer.toString(secondsLeft));
            }

            @Override
            public void onFinish() {
                gameOver();
            }
        }.start();
    }

    public void gameOver() {
        timeKeeper.setText("0");
        statusText.setText("Your Score: " + wins + " / " + tries);
        playAgain.setVisibility(View.VISIBLE);
        playing = false;
    }

    public void playAgain(View view) {
        statusText.setText("  ");
        startGame();
    }

    public void setRecord()
    {
        recordText.setText(wins + " / " + tries);
    }

    public void createRandomProblem() {
        playAgain.setVisibility(View.INVISIBLE);
        clearBtn();
        int num1 = random.nextInt(50);
        int num2 = random.nextInt(50);

        int answer = num1 + num2;

        problemText.setText(num1 + " + " + num2);

        correctBtn = random.nextInt(4);
        btns[correctBtn].setText(Integer.toString(answer));

        for(Button btn: btns) {
            if (btn.getText().toString().length() == 0) {
                int fakeAnswer = 0;
                do {
                    fakeAnswer = random.nextInt(100);
                }while (fakeAnswer == answer);
                btn.setText(Integer.toString(fakeAnswer));
            }
        }
    }

    public void answer(View view) {
        if (!playing) {
            return;
        }
        Button clickBtn = (Button)view;

        String updateGotItRightText = "Wrong!";

        if (clickBtn.getTag().toString().equals(btns[correctBtn].getTag().toString())) {
            wins += 1;
            updateGotItRightText = "Right!";
        }

        statusText.setText(updateGotItRightText);
        tries += 1;

        setRecord();
        createRandomProblem();
    }

    public void clearBtn()
    {
        for(Button btn: btns) {
            btn.setText("");
        }
    }
}
