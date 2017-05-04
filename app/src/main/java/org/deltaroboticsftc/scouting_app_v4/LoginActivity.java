package org.deltaroboticsftc.scouting_app_v4;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.deltaroboticsftc.scouting_app_v4.BackendlessLink.LoginBackendlessLink;
import org.deltaroboticsftc.scouting_app_v4.Managers.IntentManager;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoginBackendlessLink.setupLink(this);

    }

    public void onClick(View v)
    {

        String tag = v.getTag().toString();
        switch (tag)
        {
            case "login":
                final Context context = this;
                final Handler handler = new Handler();
                progressDialog = ProgressDialog.show(this, "Please Wait", "Signing In");

                EditText EMail = (EditText) findViewById(R.id.login_email);
                EditText Password = (EditText) findViewById(R.id.login_password);
                LoginBackendlessLink.attemptLogin(EMail.getText().toString(), Password.getText().toString(), this);

                int loop;
                for (loop = 0; loop < 10; loop++)
                {
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if (LoginBackendlessLink.loginGotResponse)
                            {
                                handler.removeCallbacksAndMessages(null);

                                if (!LoginBackendlessLink.loginFailed)
                                {
                                    IntentManager.MatchEditActivity(context);
                                }
                                else
                                {
                                    loginFailed();
                                }
                            }
                        }
                    }, loop * 2000);
                }

                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (LoginBackendlessLink.loginGotResponse)
                        {
                            handler.removeCallbacksAndMessages(null);

                            if (!LoginBackendlessLink.loginFailed)
                            {
                                IntentManager.MatchEditActivity(context);
                            }
                            else
                            {
                                loginFailed();
                            }
                        }
                        else
                        {
                            loginFailed();
                        }
                    }
                }, loop * 2000);
                break;

            case "cancelLogin":
                IntentManager.MatchActivity(this);
                break;
        }

    }

    private void loginFailed()
    {

        progressDialog.hide();

        if (LoginBackendlessLink.loginFailedMessage.equals("Network"))
        {
            return;
        }

        Toast toast = Toast.makeText(this, LoginBackendlessLink.loginFailedMessage, Toast.LENGTH_LONG);
        toast.show();

    }

}
