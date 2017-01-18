package com.askme.dreamhackathon.smile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.askme.dreamhackathon.smile.DatabaseBuilder.DBadapter;
import com.askme.dreamhackathon.smile.MotherTimeline.TimelineActivityPatient;
import com.makeramen.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://smilekuet.site88.net/login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_PREGNENT_DATE="pregnancydate";
    private static final String TAG_TYPE = "type";
    private static final String TAG_USERNAME = "username";
    FrameLayout loginAnimContainer;
    LinearLayout loginInfo;
    Button submitButton;
    RoundedImageView userPic;
    ImageView loadingPic;
    TextView welcomeText;
    EditText userEmail;
    EditText userPassword;
    RadioGroup menu;
    DBadapter dBadapter;

    /*    Firebase mFirebaseRef;

        private final String firebaseURL="https://askme-dreamhackathon-smile-database.firebaseio.com";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


/*        mFirebaseRef.setAndroidContext(getApplicationContext());
        mFirebaseRef = new Firebase(firebaseURL);*/

        loginAnimContainer = (FrameLayout) findViewById(R.id.loginAnimationContainer);
        loginInfo = (LinearLayout) findViewById(R.id.userInfo);
        submitButton = (Button) findViewById(R.id.submitButton);
        userPic = (RoundedImageView) findViewById(R.id.userPic);
        loadingPic = (ImageView) findViewById(R.id.loadingPic);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);
        menu = (RadioGroup) findViewById(R.id.menuChooser);


        menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.registerActivity) {
                    startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (userEmail.getText().length() == 0) {
                    Snackbar.make(userEmail, "Email field must be filled", Snackbar.LENGTH_SHORT).show();
                }
                if (userPassword.getText().length() == 0) {
                    Snackbar.make(userPassword, "Password field must be filled", Snackbar.LENGTH_SHORT).show();
                }
                if (userEmail.getText().length() != 0 && userPassword.getText().length() != 0) {

                    new PostAsync().execute();

                /*
}


                  /*  if(success==1)
                    {
                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i=new Intent(LoginActivity.this,LoginActivity.class);
                        startActivity(i);
                    }


/*                    mFirebaseRef.authWithPassword(userEmail.getText().toString(), userPassword.getText().toString(),
                            new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData)
                        {
                            dBadapter=new DBadapter(getApplicationContext());

                            String x="",tempName=userEmail.getText().toString();

                            for (int i=0;i<tempName.length();i++)
                                if(tempName.charAt(i)>='A' &&tempName.charAt(i)<='z')
                                    x=x+tempName.charAt(i);

                            Firebase temp=mFirebaseRef.child("user").child(x);

                            Query query=temp.orderByKey();
                            query.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    final User user=dataSnapshot.getValue(User.class);




                                    if (user.getType().equalsIgnoreCase("P"))
                                        dBadapter.insertUser(user.getName(),userEmail.getText().toString(),userPassword.getText().toString(),user.getContactNumber(),user.getType(),user.getPregnancyDate());
                                    else
                                        dBadapter.insertUser(user.getName(),userEmail.getText().toString(),userPassword.getText().toString(),user.getContactNumber(),user.getType(),"NONE");*/



/*
                                    SharedPreferences preferences = getSharedPreferences("askme.dreamhackathon.smile", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("askme.dreamhackathon.smile.userName", user.getName());
                                    editor.putString("askme.dreamhackathon.smile.userEmail", userEmail.getText().toString());
                                    editor.putString("askme.dreamhackathon.smile.userPassword", userPassword.getText().toString());

                                    editor.commit();
*/


                }

  /*                              @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });







                        }
                                @Override
                        public void onAuthenticationError(FirebaseError error) {
                        // Something went wrong :(
                                    String errorMsg="";
                               switch (error.getCode()) {
                                       case FirebaseError.USER_DOES_NOT_EXIST:
                                                // handle a non existing user
                                           errorMsg="User does not exist";
                                           break;
                                       case FirebaseError.INVALID_PASSWORD:
                                                // handle an invalid password
                                           errorMsg="Password doesn't match";
                                                break;
                                   case FirebaseError.NETWORK_ERROR:
                                       // handle an invalid password
                                       errorMsg="check your internet connection";
                                       break;
                                       default:
                                                // handle other errors
                                                break;
                                    }
                                    loginAnimContainer.setVisibility(View.GONE);
                                    submitButton.setVisibility(View.VISIBLE);
                                    loginInfo.setVisibility(View.VISIBLE);
                                    Snackbar.make(userEmail,"Login Failed,"+errorMsg,Snackbar.LENGTH_SHORT).show();
                            }
                    });
                }*/
               /* else
                {
                    loginAnimContainer.setVisibility(View.GONE);
                    submitButton.setVisibility(View.VISIBLE);
                    loginInfo.setVisibility(View.VISIBLE);
                }*/

                //}
            }
        });
    }
    public void successScreen(final String name, final String type,final String dayOfPregnancy)
    {
        final NotificationHandler notificationHandler=new NotificationHandler(getApplicationContext());



        notificationHandler.removeNotifications();

        loginInfo.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        loginAnimContainer.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                userPic.setVisibility(View.VISIBLE);
                TranslateAnimation anim = new TranslateAnimation(0, 0, 0, -120);
                anim.setDuration(500);
                anim.setFillAfter(true);
                anim.setAnimationListener(new TranslateAnimation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                                                    welcomeText.setText("Welcome,"+user.getName().toString());
                        welcomeText.setText("Welcome, "+name);
                        welcomeText.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                userPic.startAnimation(anim);
            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();
                if(type.equalsIgnoreCase("P"))
                {
                    Calendar calDayOfPregnancy = Calendar.getInstance();
                    String[]dates=dayOfPregnancy.split(" ");
                    calDayOfPregnancy.set(Calendar.YEAR,Integer.parseInt(dates[2]));
                    calDayOfPregnancy.set(Calendar.MONTH,Integer.parseInt(dates[1]));
                    calDayOfPregnancy.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dates[0]));

                    notificationHandler.setNotifications(calDayOfPregnancy);

                    startActivity(new Intent(LoginActivity.this,TimelineActivityPatient.class));
                }

                else
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

            }
        }, 4000);
    }


    //here my login from android to php server happen

    class PostAsync extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();




        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put("email", userEmail.getText().toString());
                params.put("password",userPassword.getText().toString());
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject json) {

            int success = 0;
            String message = "";
            String pregnancyDate="";
            String username="";
            String type="";




/*            if(userEmail.getText().toString().equalsIgnoreCase("ashikcse12@gmail.com")==true)
            pregnancyDate="03 03 2016";
            else
            pregnancyDate="21 03 2016";*/

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                Toast.makeText(LoginActivity.this, json.toString(),
                        Toast.LENGTH_LONG).show();

                try {
                    success = json.getInt(TAG_SUCCESS);
                    message = json.getString(TAG_MESSAGE);
                    pregnancyDate=json.getString(TAG_PREGNENT_DATE);
                    type=json.getString(TAG_TYPE);
                    username=json.getString(TAG_USERNAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {
                successScreen(username,type,pregnancyDate);
            }else{
                Snackbar.make(submitButton,"Login Failed",Snackbar.LENGTH_SHORT).show();
            }


        }

    }

}