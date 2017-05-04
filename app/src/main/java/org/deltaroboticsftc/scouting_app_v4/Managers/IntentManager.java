package org.deltaroboticsftc.scouting_app_v4.Managers;

import android.content.Context;
import android.content.Intent;

import org.deltaroboticsftc.scouting_app_v4.LoginActivity;
import org.deltaroboticsftc.scouting_app_v4.MatchActivity;
import org.deltaroboticsftc.scouting_app_v4.MatchEditActivity;

/**
 * Created by Luke Poellet on 4/27/2017.
 */

public class IntentManager
{

    public static void MatchActivity(Context context)
    {
        Intent intent = new Intent(context, MatchActivity.class);
        context.startActivity(intent);
    }


    public static void MatchEditActivity(Context context)
    {
        Intent intent = new Intent(context, MatchEditActivity.class);
        context.startActivity(intent);
    }


    public static void LoginActivity(Context context)
    {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
