package com.example.case2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNotification;
    int requestCode=123;
    int notificationID=888;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotification=findViewById(R.id.buttonNoti);
        btnNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new
                            NotificationChannel("default", "Default Channel",
                            NotificationManager.IMPORTANCE_DEFAULT);

                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity
                        (MainActivity.this, requestCode,
                                intent, PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.BigTextStyle bigText = new
                        NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Feeling Good Lyrics");
//
                String msg="Birds flying high, you know how i feel"+"\n Sun in the sky, you know i feel"+"\nReeds driftin' on by, you know how i feel"+"\n it's a new dawn, it's a new day, it's a new life for me"+"\n Yeah~"+"\n it's a new dawn, it's a new day, it's a new life for me"+"\n Ooooooooh..."+"\nAnd i'm feeling good";
                bigText.setSummaryText("i love Nina");

                // Build notification
                Notification.Builder builder = new Notification.Builder(MainActivity.this, "default");
                builder.setContentTitle("Feeling Good Lyrics");
                builder.setContentText("Birds flying high, you know how i feel...");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setStyle(new Notification.BigTextStyle().bigText(msg));
                builder.setAutoCancel(true);

                Notification n = builder.build();

                // This replaces the existing notification with the same ID
                notificationManager.notify(notificationID, n);
                finish();
            }
        });
    }
}

