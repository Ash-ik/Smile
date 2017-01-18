package com.askme.dreamhackathon.smile;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class NotificationHandler {
    Context context;

    public NotificationHandler(Context context) {
        this.context = context;
    }
    public void setNotifications(Calendar dayOfPregnancy)
    {
//fix it
        Calendar calendar;
        int i,weekPast= (int) ((long)(System.currentTimeMillis()-dayOfPregnancy.getTimeInMillis())/(long)(1000*7*24*60*60)),notificationID = -1;
        Log.d("smileInfo",(System.currentTimeMillis()-dayOfPregnancy.getTimeInMillis())+"");
        Log.d("smileInfo",dayOfPregnancy.get(Calendar.DAY_OF_MONTH)+" "+dayOfPregnancy.get(Calendar.MONTH)+","+dayOfPregnancy.get(Calendar.YEAR));
        for (i=weekPast;i<40;i++)
        {

            notificationID=-1;
            if(i<13)
            {
                notificationID=i%5;
            }
            if(i==13 || i==17||i==21||i==25||i==26)
            {
                notificationID=5;
                if(i>21)
                {
                    if(i==25)
                        notificationID=5;
                    else
                        notificationID=6;
                }
            }
            if(i>26&&i<36)
                if(i%2!=0)
                    notificationID=7;

            if(i>35)
                notificationID=7;

            if(notificationID!=-1)
            {
                Log.d("smileInfo",i+","+notificationID+"");
                Intent alertIntent = new Intent(context, AlertReceiver.class);

                Bundle bundle = new Bundle();

                bundle.putInt("notificationNumber",notificationID);
                alertIntent.putExtras(bundle);

                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                //alarmManager.set(AlarmManager.RTC_WAKEUP, dayOfPregnancy.getTimeInMillis()+AlarmManager.INTERVAL_DAY*7*i, PendingIntent.getBroadcast(context, notificationID, alertIntent, PendingIntent.FLAG_ONE_SHOT));
                //test
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+30*i*1000, PendingIntent.getBroadcast(context, notificationID, alertIntent, PendingIntent.FLAG_ONE_SHOT));
            }

        }
    }
    public void removeNotifications()
    {
        int i;

        for(i=0;i<40;i++) {

            Intent alertIntent = new Intent(context, AlertReceiver.class);

            Bundle bundle = new Bundle();

            bundle.putInt("notificationNumber", i);

            alertIntent.putExtras(bundle);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(PendingIntent.getBroadcast(context, i, alertIntent, PendingIntent.FLAG_ONE_SHOT));
        }

    }
}
