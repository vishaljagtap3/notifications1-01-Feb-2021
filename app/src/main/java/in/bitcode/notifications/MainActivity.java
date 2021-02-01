package in.bitcode.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnNotify, mBtnCancel;

    private NotificationCompat mNotification;
    private NotificationManagerCompat mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mBtnNotify = findViewById(R.id.btnNotify);
        mBtnCancel = findViewById(R.id.btnCancel);
        mBtnNotify.setOnClickListener(new BtnNotifyClickListener());

        /*NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);*/
        mNotificationManager = NotificationManagerCompat.from(this);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotificationManager.cancel(1);
            }
        });

    }

    private class BtnNotifyClickListener implements View.OnClickListener {
        @SuppressLint("NewApi")
        @Override
        public void onClick(View view) {

            /*@SuppressLint({"NewApi", "LocalSuppress"})
            NotificationChannel notificationChannel =
                    new NotificationChannel("in.bitcode.test", "bitcode", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setShowBadge(true);


            mNotificationManager.createNotificationChannel(notificationChannel);
            */


            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(MainActivity.this, "in.bitcode.test");
            builder.setContentTitle("BitCode Updates!");
            builder.setContentText("You got meeting at 4.00pm");
            builder.setAutoCancel(true);
            builder.setColor(Color.RED);
            builder.setOngoing(false);
            builder.setLights(Color.YELLOW, 300, 500);
            //builder.setSound("url to the sound file");
            builder.setVibrate( new long[] {500, 300, 500, 300, 500, 300} );
            builder.setCategory(NotificationCompat.CATEGORY_SOCIAL);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            builder.setLargeIcon(largeIcon);


            Intent intent = new Intent(MainActivity.this, ActNew.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(
                            MainActivity.this, 1, intent, 0
                    );
            builder.setContentIntent(pendingIntent);

            builder.addAction(R.mipmap.ic_launcher, "My Action", pendingIntent);

            mNotificationManager.notify("test_notification", 1, builder.build());
        }
    }

}