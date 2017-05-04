package org.deltaroboticsftc.scouting_app_v4.BackendlessLink;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.deltaroboticsftc.scouting_app_v4.BackendlessTables.Games;
import org.deltaroboticsftc.scouting_app_v4.Managers.NetworkManager;
import org.deltaroboticsftc.scouting_app_v4.R;

/**
 * Created by Luke Poellet on 4/27/2017.
 */

public class LoginBackendlessLink
{

    private static final String APPLICATION_ID = "E86529AC-F3AF-5DC6-FF2E-76070487A900";
    private static final String API_KEY = "09420FC2-4CC6-B7FF-FF13-09BB27650800";
    private static final String SERVER_URL = "https://api.backendless.com";

    private static AsyncCallback<BackendlessUser> callback = null;

    public static boolean loginGotResponse = false;
    public static boolean loginFailed = true;
    public static String loginFailedMessage = "Login Timed Out";


    public static void setupLink(Context context)
    {
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(context, APPLICATION_ID, API_KEY);

        callback = new AsyncCallback<BackendlessUser>()
        {
            @Override
            public void handleResponse(BackendlessUser response)
            {
                loginFailed = false;
                loginGotResponse = true;
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                loginFailed = true;
                loginFailedMessage = fault.getMessage();
                loginGotResponse = true;
            }
        };
    }


    public static void attemptLogin(String EMail, String Password, Context context)
    {
        loginGotResponse = false;
        loginFailed = true;
        loginFailedMessage = "Login Timed Out";

        if (callback == null)
        {
            setupLink(context);
        }

        if (NetworkManager.isTheNetworkActive(context))
        {
            Backendless.UserService.login(EMail, Password, callback);
        }
        else
        {
            loginGotResponse = true;
            loginFailed = true;
            loginFailedMessage = "Network";

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
