package com.example.induja.attractionsandrestaurants_app02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Induja on 3/31/2018.
 */

public class AppBroadcastReceiver extends BroadcastReceiver {
    Intent mIntent;

    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();

        if(action.equals("edu.uic.cs478.sp18.Attraction")){
            mIntent = new Intent(context.getApplicationContext(), MainActivity.class);
        }
        else{
            mIntent = new Intent(context.getApplicationContext(), SecondActivty.class);
        }

        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        context.getApplicationContext().startActivity(mIntent);
    }
}
