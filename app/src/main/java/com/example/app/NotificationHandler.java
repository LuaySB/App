package com.example.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log ;
import androidx.core.app.NotificationCompat;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationHandler extends Service{

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";

    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int NOTIFICATION_INTERVAL = 10000; //ms

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);

        //startTimer();
        createNotification();

        return START_STICKY;
    }

    @Override
    public void onCreate() {}

    @Override
    public void onDestroy (){
        stopTimerTask();
        super.onDestroy();
    }

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    public void startTimer(){
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, NOTIFICATION_INTERVAL, NOTIFICATION_INTERVAL);
    }

    public void stopTimerTask (){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask(){
        timerTask = new TimerTask(){
            public void run(){
                handler.post(new Runnable(){
                    public void run() {
                        createNotification();
                    }
                }) ;
            }
        } ;
    }

    private void createNotification(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE) ;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), default_notification_channel_id ) ;
        mBuilder.setContentTitle("Veckans Nya Erbjudanden Ute Nu!");
        mBuilder.setContentText("Logga In F??r Att Se Nu!");

        // mBuilder.setTicker( "Notification Listener Service Example");
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground );
        mBuilder.setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"NOTIFICATION_CHANNEL_NAME", importance) ;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }

        assert mNotificationManager != null;
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build()) ;
    }
}