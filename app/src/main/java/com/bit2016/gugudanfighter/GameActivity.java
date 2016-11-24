package com.bit2016.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
private static final int TIME_LIMIT = 30;

    private Timer timer = new Timer();
    private TextView tvLastTime, firstNumber , lastNumber, countNum, totalCountNum;
    private Button btnt;
    int count = 0;
    int totalCount = 0;
    int result;
    int first;
    int last;
    final static int[] btn = {R.id.button, R.id.button2, R.id.button3 , R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8 , R.id.button9};

    public void setView() {
        Random r = new Random();
        first = r.nextInt(8) + 2;
        last = r.nextInt(8) + 2;
        result = first * last;

        Set<Integer> hs = new HashSet<Integer>();
        hs.add(result);
        while(hs.size() < btn.length ){
            hs.add(r.nextInt(80) + 2);
        }

        Object[] obj = hs.toArray();
        List<Integer> btnNum = new ArrayList<Integer>();

        //list안에 무작위 숫자 추가
        for(int i = 0; i < btn.length; i++){
            btnNum.add((int)obj[i]);
        }
        //버튼배열 무작위로 섞고
        Collections.shuffle(btnNum);

        System.out.println(btnNum);

        //버튼 마다
        for (int i = 0; i < btn.length; i++) {
            ((Button) findViewById(btn[i])).setText(""+btnNum.get(i));

        }
        firstNumber.setText(Integer.toString(first));
        lastNumber.setText(Integer.toString(last));
        countNum.setText(Integer.toString(count));
        totalCountNum.setText(Integer.toString(totalCount));

        for(int i = 0; i < btn.length; i++){
            findViewById(btn[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button button = (Button)v;

                    if(result == Integer.parseInt(button.getText().toString())){
                        count++;
                        System.out.println("gigigig");
                    }
                    totalCount++;

                }
            });

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvLastTime = (TextView) findViewById(R.id.text_seconds);
        timer.schedule( new GameTimerTask(), 1000, 1000);

        firstNumber = (TextView) findViewById(R.id.text_firstNumber);
        lastNumber = (TextView) findViewById(R.id.text_lastNumber);
        countNum = (TextView) findViewById(R.id.text_count);
        totalCountNum = (TextView) findViewById(R.id.text_totalCount);


        setView();



        findViewById(R.id.button_resetmain).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }




    private void updateLastTime ( int seconds ) {
        tvLastTime.setText("" + (TIME_LIMIT-seconds));
    }

    private class GameTimerTask extends TimerTask{
        private int seconds;

        @Override
        public void run(){
                 seconds++;
            if(seconds >= TIME_LIMIT){
                timer.cancel();
                Log.d("---->", "타이머 정지");
                finish();
                // 타이머 중지
                // 결과 액티비티 호출
            }
            // UI 변경
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateLastTime(seconds);
                }
            });
        }
    }




}