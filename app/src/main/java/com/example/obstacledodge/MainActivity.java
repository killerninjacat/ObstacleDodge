package com.example.obstacledodge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;

public class MainActivity extends AppCompatActivity {
    private APIservice apiservice;
    Button startbutton,r,r1,r2,r3,r4,r5,r6,trophy;
    TextView hiscore, n1, n2, n3,s1,s2,s3;
    ListView scoreslist;
    Vibrator vi;
    List<Scoresreceive> scores;
    boolean apir=false;
    List<String> names;
    List<String> displaylist;
    List<String> receivedscores;
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
    public void scoresdialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.displayscores);
        dialog.getWindow().setLayout(1200, ViewGroup.LayoutParams.WRAP_CONTENT);
        displaylist=new ArrayList<>();
        scoreslist=dialog.findViewById(R.id.scoreslist);
        for(int j=0;j<names.size();j++)
        {
            for(int k=0;k<names.size()-j-1;k++)
                if(Integer.parseInt(receivedscores.get(k))<Integer.parseInt(receivedscores.get(k+1)))
                {
                    String te=receivedscores.get(k);
                    receivedscores.set(k,receivedscores.get(k+1));
                    receivedscores.set(k+1,te);
                    String te1=names.get(k);
                    names.set(k,names.get(k+1));
                    names.set(k+1,te1);
                }

        }
        for(int q=0;q<names.size();q++)
        {
            displaylist.add(" "+(q+1)+".   "+names.get(q)+"                      "+receivedscores.get(q)+" ");
        }
        Log.d("lastelement","first: "+displaylist.get(0));
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, R.layout.eachscore,R.id.name, displaylist);
        scoreslist.setAdapter(arr);
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiservice = ApiClient.getClient().create(APIservice.class);
        startbutton = (Button) findViewById(R.id.startbtn);
        trophy = (Button) findViewById(R.id.trophy);
        hiscore = (TextView) findViewById(R.id.hiscore);
        names=new ArrayList<>();
        receivedscores=new ArrayList<>();
        homesound=MediaPlayer.create(this,R.raw.bghome);
        homesound.setLooping(true);
        homesound.start();
        vi=(Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.obstacledodge", 0);
        int bestscore = sp.getInt("scoreget", 0);
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            hiscore.setText("  HIGHSCORE: " + bestscore+"  ");
            Call<Data> charcall=apiservice.getScores();
            charcall.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if(response.isSuccessful())
                    {
                        apir=true;
                        Data data=response.body();
                        scores=data.getScores();
                        for(int i=0;i<scores.size();i++)
                        {
                            Log.d("name","name: "+scores.get(i).getName());
                            String stemp=scores.get(i).getName();
                            names.add(i,stemp);
                            receivedscores.add(i,Integer.toString(scores.get(i).getScore()));
                        }
                        Log.d("myscoreadded","reached");
                        names.add(names.size(),"You");
                        receivedscores.add(receivedscores.size(),Integer.toString(bestscore));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "failed to get scores", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", "Error: " + response.code());
                        Log.d("MainActivity", "Message: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Connect to the internet for an enhanced experience", Toast.LENGTH_SHORT).show();
                    Log.d("msg","msg "+t.getMessage());

                }
            });
            startbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vi.vibrate(100);
                    charselect();
                }
            });
            trophy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(apir)
                    scoresdialog();
                    else
                        Toast.makeText(MainActivity.this, "CONNECT TO THE INTERNET AND RESTART APP TO SEE SCORES", Toast.LENGTH_SHORT).show();
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