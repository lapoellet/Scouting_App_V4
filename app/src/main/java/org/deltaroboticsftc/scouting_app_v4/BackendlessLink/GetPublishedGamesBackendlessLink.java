package org.deltaroboticsftc.scouting_app_v4.BackendlessLink;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import org.deltaroboticsftc.scouting_app_v4.BackendlessTables.Games;
import org.deltaroboticsftc.scouting_app_v4.Managers.NetworkManager;
import org.deltaroboticsftc.scouting_app_v4.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Luke Poellet on 5/2/2017.
 */

public class GetPublishedGamesBackendlessLink
{

    private static final String APPLICATION_ID = "E86529AC-F3AF-5DC6-FF2E-76070487A900";
    private static final String API_KEY = "09420FC2-4CC6-B7FF-FF13-09BB27650800";
    private static final String SERVER_URL = "https://api.backendless.com";

    private static AsyncCallback<List<Games>> callback = null;
    private static DataQueryBuilder dataQueryBuilder  = DataQueryBuilder.create();

    private static List<Games> publishedGames = new ArrayList<>();

    public static void setupLink(Context context)
    {
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(context, APPLICATION_ID, API_KEY);

        dataQueryBuilder.setWhereClause("Published = true");
        dataQueryBuilder.setSortBy("Game_Year");

        callback = new AsyncCallback<List<Games>>()
        {
            @Override
            public void handleResponse(List<Games> response)
            {
                publishedGames = response;
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                Log.d("Fault-GetPublishedGames", fault.getMessage());
            }
        };
    }

    public static void getPublishedGames(Context context)
    {

        if (callback == null)
        {
            setupLink(context);
        }

        if (NetworkManager.isTheNetworkActive(context))
        {
            new Games().findAsync(dataQueryBuilder, callback);
        }
        else
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("No Internet connection.");
            alertDialog.setMessage("Make sure Wi-Fi or cellular data is turned on.");
            alertDialog.setIcon(R.drawable.ic_error_outline_black_24dp);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

}
