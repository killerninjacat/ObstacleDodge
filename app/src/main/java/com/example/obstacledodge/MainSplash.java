package com.example.obstacledodge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSplash extends AppCompatActivity {
    private APIservice apiservice;
    TextView tipbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        tipbox=(TextView)findViewById(R.id.tipbox);
        apiservice = ApiClient.getClient().create(APIservice.class);
        Call<Data> tipcall=apiservice.randomtip();
        Log.d("test", "outside");
        tipcall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful())
                {
                    Data data=response.body();
                    String tip=data.getTip();
                    Log.d("tip", "tip: "+tip);
                    tipbox.setText(tip);
                    Log.d("tip", "tip: " + tip);
                }
                else {
                    tipbox.setText("not got");
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("tip", "failed");
                Log.d("msg","msg "+t.getMessage());
                tipbox.setText("hello");
                Toast.makeText(MainSplash.this, "Connect to the internet for an enhanced experience", Toast.LENGTH_SHORT).show();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainSplash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}