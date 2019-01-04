package com.example.anne.gudetamatimer2;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SeekBar seekbar;
    TextView timerTextView;
    Button controllerButton;
    CountDownTimer countDownTimer;
    Boolean counterIsActive= false;

    public void resetTimer(){

        timerTextView.setText("0:30");
        seekbar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("start");
        seekbar.setEnabled(true);
        counterIsActive=false;
    }



    public void updateTime(int progress){
        int minutes= (int)progress/60;
        int seconds= progress-minutes*60;
        if (seconds<= 9){
            timerTextView.setText(minutes+":"+"0"+seconds);
        }
        else {
            timerTextView.setText(minutes + ":" + seconds);
        }


    }

    public void controlTimer(View view){

        if (counterIsActive==false) {
            counterIsActive = true;
            seekbar.setEnabled(false);
            controllerButton.setText("stop");

            Log.i("info", "clicked");
            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override

                public void onTick(long millisUntilFinished) {
                    updateTime((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.gudetama2);
                    //above: must use getApplicationContext when inside another function
                    mplayer.start();
                }
            }.start();
        }
        else{
            resetTimer();


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar=(SeekBar)findViewById(R.id.seekBar);
        timerTextView=(TextView)findViewById(R.id.timeDisplay);
        controllerButton=(Button)findViewById(R.id.button2);

        seekbar.setMax(600);
        seekbar.setProgress(30);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTime(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}
