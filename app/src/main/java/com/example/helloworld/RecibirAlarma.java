package com.example.helloworld;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class RecibirAlarma extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
                Intent Service1 = new Intent(context, NotificacionAlarma.class);
                intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
                ContextCompat.startForegroundService(context, intent);
        }
}
