package com.askme.dreamhackathon.smile;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    int notificationNumber;
    String tickerString="";
    PendingIntent notifyUserIntent;

    String[] notificationMsg={"সকল প্রকার ভারী কাজ এড়িয়ে চলুন,আপনার সুস্থতা আপনার শিশুর সুস্থতা সুনিশ্চিত করে","পরিমিত পরিমাণ বিশ্রাম নিন,দৈনিক কমপক্ষে ছয় ঘন্টা ঘুম আবশ্যক","বেশি বেশি করে মিষ্টি খাবার ও ফলমূল গ্রহণ করুন","গর্ভাবস্থায় আনারস খাওয়া থেকে বিরত থাকুন","ধূমপান বা অন্য সকল প্রক্রার নেশাজাত দ্রব্য পরিহার করুন","T.T টিকা নেবার সময় হয়ে গেছে,সময়মত টিকা নিন","গর্ভাবস্থার পাঁচ থেকে ছয় মাস পরে শিশুর নড়াচড়া খুব স্বাভাবিক ব্যপার,ঘাবড়াবেন না","ডাক্তারের পরামর্শ নিন"};

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationNumber = intent.getIntExtra("notificationNumber",0);


            notifyUserIntent = PendingIntent.getActivity(context, notificationNumber, new Intent(context, DailyTips.class), PendingIntent.FLAG_ONE_SHOT);
            tickerString=notificationMsg[notificationNumber];


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.home1)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(tickerString)
                .setTicker(tickerString);


        mBuilder.setContentIntent(notifyUserIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }

}