package com.example.obstacledodge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    int gcheck, score, plyagn = 0,ru1;int soundcheck=0;
    static int chch=0;
    boolean gover = false;
    Button homebutton, playagain,homeongover;
    MediaPlayer runsound;
    String img;
    public String getimgurl()
    {
        return img;
    }

    public void gameoverdialog() {
        final Dialog dialog = new Dialog(MainActivity2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(500, 500);
        dialog.setContentView(R.layout.gameoverpopup);
        homebutton = (Button) findViewById(R.id.homebutton);
        playagain = (Button) findViewById(R.id.playagain);
        TextView txt = dialog.findViewById(R.id.displayscore);
        Button homebutton = dialog.findViewById(R.id.homebutton);
        Button playagain = dialog.findViewById(R.id.playagain);
        txt.setText("Your Score: " + score);
        final MediaPlayer strt=MediaPlayer.create(this, R.raw.startgame);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chch=1;
                getIntent().putExtra("stch", 1);
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
                strt.start();
                dialog.dismiss();
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chch=1;
                Intent in = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(in);
            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("soundcheck", "soundcheck: " + soundcheck);

        if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_main2);
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        LinearLayout container = findViewById(R.id.container);
        CanvasMain canv = new CanvasMain(this);
        plyagn = getIntent().getIntExtra("stch", 0);
        img=getIntent().getStringExtra("imgurl");
            SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.obstacledodge", 0);
            SharedPreferences.Editor editor = sp.edit();
            final MediaPlayer goversound=MediaPlayer.create(this, R.raw.gameover);
        if (plyagn == 1) {
            canv.Resetvals();
            ru1=sp.getInt("selected",1);
            canv.setrun2(ru1);
            plyagn = 0;
            container.addView(canv);
        }
        else
        {
            Random r7=new Random();
            ru1=getIntent().getIntExtra("runn",r7.nextInt(3));
            editor.putInt("selected", ru1);
            editor.commit();
            canv.setrun2(ru1);
            Log.d("setrun2", "setrun2: ");
            container.addView(canv);
        }
            Log.d("ru1", "ru1: " + ru1);
            homeongover=(Button) findViewById(R.id.homeongover);
        score = getIntent().getIntExtra("score", 0);
        Log.d("aftergetintent", "score: " + score);
        gover = getIntent().getBooleanExtra("overdialog", false);
        gcheck = getIntent().getIntExtra("startcheck", 0);
            if(soundcheck==0&&!gover)
            {
                runsound=MediaPlayer.create(this,R.raw.bggame);
                runsound.setLooping(true);
                runsound.start();
                soundcheck=1;
            }
        Log.d("aftergetintent", "gcheck: " + gcheck);
        int best = sp.getInt("scoreget", 0);
        if (score > best) {
            editor.putInt("scoreget", score);
            editor.commit();
        }
        Log.d("goveroutsideif", "gover: " + gover);
        if (gover) {
            goversound.start();
            gameoverdialog();
            homeongover.setVisibility(View.VISIBLE);
        }
        Log.d("outsideoncreate", "gcheck: " + gcheck);

        if (gcheck == 0) {
            Log.d("insideif", "gcheck: " + gcheck);
            canv.Movestart();
            gcheck = 0;
        }
    }
        else
            setContentView(R.layout.activity_main);
        if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            homeongover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent inten = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(inten);
                }
            });
        }

}
    @Override
    protected void onPause() {
        super.onPause();

        if (runsound != null && runsound.isPlaying()) {
            runsound.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

            if (runsound != null && !runsound.isPlaying()) {
                runsound.start();
            }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

            if (runsound != null) {
                runsound.stop();
                runsound.release();
                runsound = null;
            }

    }

    @Override
    public void onBackPressed() {
        Intent in10=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(in10);
    }
}