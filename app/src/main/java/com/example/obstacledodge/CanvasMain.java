package com.example.obstacledodge;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.display.DisplayManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CanvasMain extends View {
    private int rad = 80, ob_w = 90, ob1_h = 400, ob2_h = 200, ob_speed = 20, cx,cx1, cy,cy1,eggpos;
    private Paint cir_p, ob_p,ob_p1,ob_p2,textpaint;
    private List<RectF> obs,obscomp;
    private List<Bitmap> eggs;
    private List<Integer> veldir;
    private Bitmap runner,chaser, runnerhit,runner1,runner2,runner3,runner4,runner5,runner6,runner7,egg;
    boolean jump,jump1,gover=false,strtgme=false,pu=false;
    private int g=0;
    float ob_speed_constant,refrate,obvertical;
    int go1=0,score=0,obstacle1=0,obcount=0,getrun,obgap,putime=200,puelapsed,dircheck=-1;
    private long t1 = 0;
    float vely,vely1=-67,vely3,grav=3;
    final MediaPlayer h01=MediaPlayer.create(getContext(), R.raw.hitonce);
    Vibrator vibr;
    Typeface scorefont;
    public void Movestart()
    {
        strtgme=true;
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        },0);
    }
    public static float getRefreshRate(Context context) {
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        if (dm != null) {
            Display di;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                di = dm.getDisplay(Display.DEFAULT_DISPLAY);
            else
                di = dm.getDisplay(0);

            if (di != null) {
                float refreshRate = di.getRefreshRate();
                return refreshRate;
            }
        }
        return 60.0f;
    }
    public void setrun2(int r9)
    {
        if(r9==1)
            runner=runner1;
        else if(r9==2)
            runner=runner2;
        else if(r9==3)
            runner=runner3;
        else if(r9==4)
            runner=runner4;
        else if(r9==5)
            runner=runner5;
        else if(r9==6)
            runner=runner6;
    }
    public void Resetvals()
    {
        Log.d("resetvals", "resetvals: ");
        gover=false;
        score=0;
        g=0;
        go1=0;
        strtgme=false;
        cx=100+2*rad+200;
        cx1=cx-500;
        cy=getHeight()-260;
        cy1=getHeight()-260;
        jump=false;
        obcount=0;
        obstacle1=0;
        vely=0;
        obs.clear();
        veldir.clear();
        obscomp.clear();
        newobs(getWidth(),getHeight());
        sendvals();
        invalidate();
    }


    public CanvasMain(Context context) {
        super(context);
        cir_p = new Paint();
        ob_p = new Paint();
        ob_p1 = new Paint();
        ob_p2=new Paint();
        Resources res = context.getResources();
        scorefont = ResourcesCompat.getFont(context, R.font.bangers);
        textpaint=new Paint();
        textpaint.setColor(Color.BLACK);
        textpaint.setTextSize(120);
        textpaint.setTypeface(scorefont);
        runner = BitmapFactory.decodeResource(getResources(), R.drawable.runner);
        runner1 = BitmapFactory.decodeResource(getResources(), R.drawable.runner1);
        runner2 = BitmapFactory.decodeResource(getResources(), R.drawable.runner2);
        runner3 = BitmapFactory.decodeResource(getResources(), R.drawable.runner3);
        runner4 = BitmapFactory.decodeResource(getResources(), R.drawable.runner4);
        runner5 = BitmapFactory.decodeResource(getResources(), R.drawable.runner5);
        runner6 = BitmapFactory.decodeResource(getResources(), R.drawable.runner6);
        runner7 = BitmapFactory.decodeResource(getResources(), R.drawable.runner7);
        runnerhit = BitmapFactory.decodeResource(getResources(), R.drawable.hitrunner);
        egg = BitmapFactory.decodeResource(getResources(), R.drawable.bonus);
        chaser=BitmapFactory.decodeResource(getResources(),R.drawable.chaser);
        cir_p.setColor(Color.CYAN);
        ob_p.setColor(Color.parseColor("#5C06FA"));
        ob_p2.setColor(Color.parseColor("#5B1600"));
        ob_p1.setColor(Color.RED);
        obs = new ArrayList<>();
        veldir=new ArrayList<>();
        obscomp = new ArrayList<>();
    }
    protected void onSizeChanged(int w2,int h2,int w1,int h1)
    {
        super.onSizeChanged(w2,h2,w1,h1);
        cx=100+2*rad+200;
        cy=h2-350;
        cy1=h2-350;
        newobs(w2,h2);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (strtgme)
        {
            super.onDraw(canvas);
        int base = getHeight() - 100;
        vibr=(Vibrator)getContext().getSystemService(getContext().VIBRATOR_SERVICE);
            long ctime = System.currentTimeMillis();
            long rantime = ctime - t1;
            t1 = ctime;
            Random random=new Random();
            refrate=getRefreshRate(getContext());

            if(refrate>110) {
            obvertical=2.5f;
            if(obcount>=30)
                obvertical=5.5f;
            else if(obcount>=40)
                obvertical=8.5f;
            if(obcount<7) {
                ob_speed = 10;
                obgap = 1250+10*random.nextInt(5);
            }
            else if(obcount>=7&&obcount<=15) {
                ob_speed = 11;
                obgap = 1450+10*random.nextInt(5);
            }
            else if(obcount>=16&&obcount<=25) {
                ob_speed = 12;
                obgap = 1650+10*random.nextInt(5);
            }
            else {
                ob_speed = 13;
                obgap = 1750+10*random.nextInt(5);
            }
        }
            else if(refrate>80)
            {
                vely1=-55;
                grav=1.8f;
                obvertical=3.3f;
                if(obcount>=30)
                    obvertical=7.33f;
                else if(obcount>=40)
                    obvertical=11.33f;
                if(obcount<7) {
                    vely1=-56;
                    ob_speed = 14;
                    obgap = 1250 + 10*random.nextInt(5);
                }
                else if(obcount>=7&&obcount<=15) {
                    ob_speed = 15;
                    obgap = 1450 + 10*random.nextInt(5);
                }
                else if(obcount>=16&&obcount<=25) {
                    ob_speed = 16;
                    obgap = 1650 + 10*random.nextInt(5);
                }
                else {
                    ob_speed = 18;
                    obgap = 1750 + 10*random.nextInt(5);
                }
            }
            else {
                obvertical=5;
                if(obcount>=30)
                    obvertical=11;
                else if(obcount>=40)
                    obvertical=17;
                if(obcount<7) {
                    ob_speed = 20;
                    obgap = 1250+ 10*random.nextInt(5);
                }
                else if(obcount>=7&&obcount<=15) {
                    ob_speed = 22;
                    obgap = 1450+ 10*random.nextInt(5);
                }
                else if(obcount>=16&&obcount<=25) {
                    ob_speed = 24;
                    obgap = 1650+ 10*random.nextInt(5);
                }
                else {
                    ob_speed = 26;
                    obgap = 1750+ 10*random.nextInt(5);
                }
            }
            /*if(obcount>=2)
                newegg();
                for(Bitmap bi:eggs)
                {
                    canvas.drawBitmap(bi,eggpos,getHeight()-300,null);
                    eggpos-=ob_speed;
                }*/
            ob_speed_constant = (ob_speed * rantime*60) / 1000.0f;
            canvas.drawText("SCORE: "+score,getWidth()-550,190,textpaint);
        canvas.drawBitmap(runner,cx, cy, null);
        canvas.drawBitmap(chaser,cx1,cy1,null);
        for (int i1=0;i1<obs.size();i1++) {
            RectF ob1=obs.get(i1);
            ob1.offset(-ob_speed, 0);
            if(obcount>=20)
                if(ob1.height()==200)
                ob1.offset(0,-obvertical*veldir.get(i1));
            if(ob1.top<=0||ob1.bottom>=getHeight()-150) {
                    veldir.set(i1,veldir.get(i1) * -1);
            }
            if(ob1.height()==200)
            canvas.drawRect(ob1, ob_p);
            else
                canvas.drawRect(ob1,ob_p2);
            if (!obscomp.contains(ob1)&&!gover && RectF.intersects(ob1, cirtorect())) {
                if (ob1.height() == 400.0 || g >= 1) {
                    gover = true;Log.d("gameovercall", "gameovercall ");
                    float f9=cirtorectchaser().right-ob1.left;
                    Log.d("chaserjumponhit", "chaserjumponhit: "+f9);

                    gameover();

                } else if (ob1.height() == 200.0) {
                    h01.start();
                    ob_speed-=3;
                    g++;
                    cx1=cx-300;
                    canvas.drawRect(ob1,ob_p1);
                    vibr.vibrate(100);
                    ob_p=ob_p1;
                    runner= runnerhit;
                }
                obscomp.add(ob1);
            } else if (!obscomp.contains(ob1)&&!gover && ob1.right + ob_speed < cx - 2*rad) {
                score += 10;
                obscomp.add(ob1);
            }
            if(cirtorectchaser().right-ob1.left>-480&&cirtorectchaser().right-ob1.left<-460)/*||cirtorectchaser().right-ob1.left<=-385)*/
                jumpball1();

        }
        for (int i = obs.size() - 1; i >= 0; i--) {
            RectF ob3 = obs.get(i);
            if (ob3.right <= 0) {
                obs.remove(i);
                veldir.remove(i);
                obcount++;
            }
        }
        if (!gover && obs.size() < 5) {
            newobs(getWidth(), getHeight());
        }
        if (jump) {
            vely += grav;
            cy += vely;
            if (cy >= base - 250) {
                cy = base - 250;
                jump = false;
            }
        }
            if (jump1) {
                vely3 += grav;
                cy1 += vely3;
                if (cy1 >= base - 250) {
                    cy1 = base - 250;
                    jump1 = false;
                }
            }
        if (!gover)
            invalidate();
    }

    }
    private RectF cirtorect()
    {
        return new RectF(cx-2*rad+120,cy-2*rad+130,cx+2*rad+50,cy+2*rad-30);
    }
    private RectF cirtorectchaser()
    {
        return new RectF(cx-2*rad+120-500,cy1-2*rad,cx+2*rad+50-500,cy1+2*rad-30);
    }
    private void newobs(int wi,int he)
    {
        Random random=new Random();
        int obh=random.nextBoolean()?ob1_h:ob2_h;
        int oby=he-obh-150;
        if(obcount>=20&&obh==200) {
            oby = oby - 180*random.nextInt(5);
            Log.d("initial", "oby: "+oby);
        }
        int obx=wi;
        for(RectF ob2:obs)
            obx+=ob2.width()+obgap;
        RectF ob2=new RectF(obx,oby,obx+ob_w,oby+obh);
        obs.add(ob2);
        veldir.add(1);
    }
    /*private void newegg()
    {
       Random ra=new Random();
       int ra1=ra.nextInt(5);
       int eggdis=0,co=0;
        egg = BitmapFactory.decodeResource(getResources(), R.drawable.bonus);
       if(ra1==3) {
           for(Bitmap bi1:eggs)
           {
               eggdis+=bi1.getWidth();
               co++;
           }
           if(eggdis>=(egg.getWidth()*co)
           eggs.add(egg);
       }
    }*/
    public void sendvals()
    {
        Intent in=new Intent(getContext(),MainActivity2.class);
        in.putExtra("overdialog",gover);
        in.putExtra("score",score);
        in.putExtra("startcheck",go1);
        getContext().startActivity(in);
    }
    private void gameover()
    {
        gover=true;
        strtgme=false;
        go1=1;
        sendvals();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(!gover&&event.getAction()==MotionEvent.ACTION_DOWN&&!jump)
            jumpball();
        return super.onTouchEvent(event);
    }
    private void jumpball()
    {
        vely=vely1 ;
        jump=true;
    }
    private void jumpball1()
    {
        Log.d("jumpball1", "jumpball1called");
        vely3=vely1;
        jump1=true;
    }
}



