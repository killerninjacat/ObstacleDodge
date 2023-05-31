package com.example.obstacledodge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startbutton,r,r1,r2,r3,r4,r5,r6;
    TextView hiscore;
    Vibrator vi;
    int ru;
    MediaPlayer homesound;
    public void charselect() {
        final Dialog dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.getWindow().setLayout(500, 500);
        dialog1.setContentView(R.layout.chooserunner);
        r = dialog1.findViewById(R.id.r);
        r1 = dialog1.findViewById(R.id.r1);
        r2 = dialog1.findViewById(R.id.r2);
        r3 = dialog1.findViewById(R.id.r3);
        r4 = dialog1.findViewById(R.id.r4);
        r5 = dialog1.findViewById(R.id.r5);
        r6 = dialog1.findViewById(R.id.r6);
        final MediaPlayer strt=MediaPlayer.create(this, R.raw.startgame);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=0;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=1;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=2;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=3;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=4;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=5;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=6;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("runn",ru);
                startActivity(i);
            }
        });
        dialog1.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton = (Button) findViewById(R.id.startbtn);
        hiscore = (TextView) findViewById(R.id.hiscore);
        homesound=MediaPlayer.create(this,R.raw.bghome);
        homesound.setLooping(true);
        homesound.start();
        vi=(Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.obstacledodge", 0);
        int bestscore = sp.getInt("scoreget", 0);
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            hiscore.setText("HIGHSCORE: " + bestscore);
            startbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vi.vibrate(100);
                    charselect();
                }
            });

        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (homesound != null && homesound.isPlaying()) {
            homesound.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (homesound != null && !homesound.isPlaying()) {
            homesound.start();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (homesound != null) {
            homesound.stop();
            homesound.release();
            homesound = null;
        }

    }
    @Override
    public void onBackPressed() {

    }
}