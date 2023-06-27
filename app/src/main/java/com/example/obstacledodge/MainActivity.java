package com.example.obstacledodge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;

public class MainActivity extends AppCompatActivity {
    private APIservice apiservice;
    Button startbutton,info,settings;
    ImageView r,r1,r2,c1,c2,c3;
    ImageView trophy;
    TextView hiscore, n1, n2, n3,d1,d2,d3;
    ListView scoreslist;
    List<String> imgurls,descriptions,cnames,imgurls1,descriptions1,cnames1;
    Vibrator vi;
    List<Scoresreceive> scores;
    List<Characterreceive> chars,chars1;
    Characterreceive charr;
    boolean apir=false,chk=false;
    List<String> names;
    List<String> chnames;
    List<String> displaylist;
    List<String> receivedscores;
    int ru,ru1;
    MediaPlayer homesound;
    public void chaserselect()
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.chaserdetails);
        dialog.getWindow().setLayout(2000, ViewGroup.LayoutParams.WRAP_CONTENT);
        n1=dialog.findViewById(R.id.n1);
        n2=dialog.findViewById(R.id.n2);
        n3=dialog.findViewById(R.id.n3);
        d1=dialog.findViewById(R.id.d1);
        d2=dialog.findViewById(R.id.d2);
        d3=dialog.findViewById(R.id.d3);
        c1=dialog.findViewById(R.id.c1);
        c2=dialog.findViewById(R.id.c2);
        c3=dialog.findViewById(R.id.c3);
        n1.setText(cnames1.get(0));
        n2.setText(cnames1.get(1));
        n3.setText(cnames1.get(2));
        d1.setText(descriptions1.get(0));
        d2.setText(descriptions1.get(1));
        d3.setText(descriptions1.get(2));
        if(imgurls1!=null) {
            Glide.with(MainActivity.this)
                    .load(imgurls1.get(0))
                    .apply(new RequestOptions())
                    .into(c1);
            Glide.with(MainActivity.this)
                    .load(imgurls1.get(1))
                    .apply(new RequestOptions())
                    .into(c2);
            Glide.with(MainActivity.this)
                    .load(imgurls1.get(2))
                    .apply(new RequestOptions())
                    .into(c3);
        }
        else
            Toast.makeText(MainActivity.this, "failed to get characters", Toast.LENGTH_SHORT).show();
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi.vibrate(100);
                ru1=0;
                charselect();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi.vibrate(100);
                ru1=1;
                charselect();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi.vibrate(100);
                ru1=2;
                charselect();
            }
        });
        dialog.show();
    }
    public void charselect() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.characterdetails);
        dialog.getWindow().setLayout(2000, ViewGroup.LayoutParams.WRAP_CONTENT);
        n1=dialog.findViewById(R.id.n1);
        n2=dialog.findViewById(R.id.n2);
        n3=dialog.findViewById(R.id.n3);
        d1=dialog.findViewById(R.id.d1);
        d2=dialog.findViewById(R.id.d2);
        d3=dialog.findViewById(R.id.d3);
        c1=dialog.findViewById(R.id.c1);
        c2=dialog.findViewById(R.id.c2);
        c3=dialog.findViewById(R.id.c3);
        n1.setText(cnames.get(0));
        n2.setText(cnames.get(1));
        n3.setText(cnames.get(2));
        d1.setText(descriptions.get(0));
        d2.setText(descriptions.get(1));
        d3.setText(descriptions.get(2));
        if(imgurls!=null) {
            Glide.with(MainActivity.this)
                    .load(imgurls.get(0))
                    .apply(new RequestOptions())
                    .into(c1);
            Glide.with(MainActivity.this)
                    .load(imgurls.get(1))
                    .apply(new RequestOptions())
                    .into(c2);
            Glide.with(MainActivity.this)
                    .load(imgurls.get(2))
                    .apply(new RequestOptions())
                    .into(c3);
        }
        else
            Toast.makeText(MainActivity.this, "failed to get characters", Toast.LENGTH_SHORT).show();
        final MediaPlayer strt=MediaPlayer.create(this, R.raw.startgame);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=0;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("imgurl",imgurls.get(0));
                i.putExtra("runn",ru);
                i.putExtra("runn1",ru1);
                startActivity(i);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=1;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("imgurl",imgurls.get(0));
                i.putExtra("runn",ru);
                i.putExtra("runn1",ru1);
                startActivity(i);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                vi.vibrate(100);
                ru=2;
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("imgurl",imgurls.get(0));
                i.putExtra("runn",ru);
                i.putExtra("runn1",ru1);
                startActivity(i);
            }
        });
        dialog.show();
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
        trophy = (ImageView) findViewById(R.id.trophy);
        hiscore = (TextView) findViewById(R.id.hiscore);
        names=new ArrayList<>();
        imgurls=new ArrayList<>();
        cnames=new ArrayList<>();
        descriptions=new ArrayList<>();
        imgurls1=new ArrayList<>();
        cnames1=new ArrayList<>();
        descriptions1=new ArrayList<>();
        chnames=new ArrayList<>();
        receivedscores=new ArrayList<>();
        vi=(Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("com.example.obstacledodge", 0);
        int bestscore = sp.getInt("scoreget", 0);
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            homesound=MediaPlayer.create(this,R.raw.bghome);
            homesound.setLooping(true);
            homesound.start();
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
                        Log.d("error 422","error msg: "+response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.d("msg","msg "+t.getMessage());

                }
            });
            CharacterRequest request = new CharacterRequest("player");
            CharacterRequest request1 = new CharacterRequest("chaser");
            Call<Data> charcall1=apiservice.getallcharacters(request);
            charcall1.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if(response.isSuccessful())
                    {
                        Data data=response.body();
                        chars=data.getCharacters();
                        Log.d("characters","responsebody: ");
                        for(int i=0;i<chars.size();i++) {
                            imgurls.add(chars.get(i).getImageUrl());
                            cnames.add(chars.get(i).getName());
                            descriptions.add(chars.get(i).getDescription());
                        }
                        chk=true;
                        Log.d("success","reached1: "+imgurls.size());
                    }
                    else {
                        Log.d("error", "Error: " + response.code());
                        Log.d("message", "Message: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.d("msg","msg "+t.getMessage());

                }
            });
            Call<Data> charcall2=apiservice.getallcharacters(request1);
           charcall2.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if(response.isSuccessful())
                    {
                        Data data1=response.body();
                        chars1=data1.getCharacters();
                        for(int i=0;i<chars1.size();i++) {
                            imgurls1.add(chars1.get(i).getImageUrl());
                            cnames1.add(chars1.get(i).getName());
                            descriptions1.add(chars1.get(i).getDescription());
                        }
                    }
                    else {
                        Log.d("error", "Error: " + response.code());
                        Log.d("message", "Message: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.d("msg","msg "+t.getMessage());

                }
            });
            startbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vi.vibrate(100);
                    if(chk)
                    chaserselect();
                    else
                        Toast.makeText(MainActivity.this, "CONNECT TO THE INTERNET AND RESTART THE APP", Toast.LENGTH_SHORT).show();
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