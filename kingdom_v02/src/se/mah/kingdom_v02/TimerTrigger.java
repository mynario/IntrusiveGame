package se.mah.kingdom_v02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TimerTrigger extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
    	 
        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(context, Event.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        context.startActivity(intent2);

    } 

}