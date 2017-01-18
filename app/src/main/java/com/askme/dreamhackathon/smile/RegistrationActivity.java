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
import android.widget.ScrollView;

import com.askme.dreamhackathon.smile.MotherTimeline.TimelineActivityPatient;
import com.makeramen.RoundedImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://smilekuet.site88.net/register.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String finalDate="";
    FrameLayout registrationAnimContainer;
    ScrollView registrationInfo;
    Button submitButton;
    Button FinalSubmit;
    RoundedImageView userPic;
    ImageView loadingPic;
    EditText userName;
    EditText userEmail;
    EditText userPassword;
    EditText userConfirmPassword;
    EditText userPhoneNumber;
    EditText userPregnancyDate;
    RadioGroup typeSelector;
    LinearLayout onlyForPatient;
    String choosenDate="";
    Boolean isPictureSelected=true;
    int FinalDay,FinalMonth,FinalYear;
    GetMyLocation myLocation;
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    String userType="P";
//    private final String firebaseURL="https://askme-dreamhackathon-smile-database.firebaseio.com";
//    private Firebase mFirebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myLocation = new GetMyLocation(getApplicationContext());

/*        mFirebaseRef.setAndroidContext(getApplicationContext());
        mFirebaseRef = new Firebase(firebaseURL);*/

        registrationAnimContainer = (FrameLayout) findViewById(R.id.registrationAnimationContainer);
        registrationInfo = (ScrollView) findViewById(R.id.userInfo);
        submitButton = (Button) findViewById(R.id.submitButton);
        FinalSubmit = (Button) findViewById(R.id.finalSubmit);
        userPic = (RoundedImageView) findViewById(R.id.userPic);
        loadingPic = (ImageView) findViewById(R.id.loadingPic);
        userName = (EditText) findViewById(R.id.userName);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);
        userConfirmPassword = (EditText) findViewById(R.id.userConfirmPassword);
        userPhoneNumber = (EditText) findViewById(R.id.userPhone);
        userPregnancyDate = (EditText) findViewById(R.id.patientPregnancyDate);
        typeSelector = (RadioGroup) findViewById(R.id.typeSelection);
        onlyForPatient = (LinearLayout) findViewById(R.id.onlyForPatient);


        typeSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.patientSelected:
                        userType = "P";
                        onlyForPatient.setVisibility(View.VISIBLE);
                        break;
                    case R.id.doctorSelected:
                        userType = "D";
                        onlyForPatient.setVisibility(View.GONE);
                        break;
                    case R.id.healthAgentSelected:
                        userType = "H";
                        onlyForPatient.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationInfo.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                registrationAnimContainer.setVisibility(View.VISIBLE);
                if (userPassword.getText().length() != 0 && userPassword.getText().toString().equalsIgnoreCase(userConfirmPassword.getText().toString()) == false) {
                    Snackbar.make(userPassword, "Password doesn't match", Snackbar.LENGTH_SHORT).show();
                }
                if (userEmail.getText().length() == 0 || userPassword.getText().length() == 0 || userConfirmPassword.getText().length() == 0 || userPhoneNumber.getText().length() == 0 || (userType.equalsIgnoreCase("P") && userPregnancyDate.getText().length() == 0)) {
                    Snackbar.make(userPassword, "Please Fill up all the information", Snackbar.LENGTH_SHORT).show();
                }

                if (userEmail.getText().length() != 0 && userPassword.getText().length() != 0 && userConfirmPassword.getText().length() != 0 && userPassword.getText().toString().equalsIgnoreCase(userConfirmPassword.getText().toString()) && userPhoneNumber.getText().length() != 0 && ((userType.equalsIgnoreCase("P") && userPregnancyDate.getText().length() != 0) || userType.equalsIgnoreCase("P") == false)) {
                    loadingPic.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingPic.setVisibility(View.GONE);
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
                                    userName.setVisibility(View.VISIBLE);
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
                            FinalSubmit.setVisibility(View.VISIBLE);
                        }
                    }, 4000);
                } else {
                    registrationAnimContainer.setVisibility(View.GONE);
                    submitButton.setVisibility(View.VISIBLE);
                    registrationInfo.setVisibility(View.VISIBLE);
                }

            }
        });

        FinalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new PostAsync().execute();

            }

        });
        userPregnancyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregnancyDateChooser(v);
            }
        });

    }
    public void pregnancyDateChooser(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                RegistrationActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.showYearPickerFirst(true);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        if (dayOfMonth < 10)
            choosenDate = "0" + dayOfMonth + " " + months[monthOfYear] + "," + year;
        else
            choosenDate = dayOfMonth + " " + months[monthOfYear] + "," + year;






        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        thatDay.set(Calendar.MONTH,monthOfYear); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);


        Calendar today = Calendar.getInstance();


        Boolean result=getDateDiffString(userPregnancyDate,thatDay,today);

        if(result==true)
        {
            userPregnancyDate.setText(choosenDate);
            finalDate=dayOfMonth+" "+(monthOfYear+1)+" "+year;
            FinalDay = dayOfMonth;
            FinalMonth = monthOfYear + 1;
            FinalYear = year;
        }
    }
    public Boolean getDateDiffString(View view,Calendar datePickerDate, Calendar currentDate)
    {
        long timeOne = datePickerDate.getTimeInMillis();
        long timeTwo = currentDate.getTimeInMillis();
        long oneDay = 24 * 60 * 60 * 1000;
        long dates = (timeTwo - timeOne) / oneDay;

        if (dates < 0)
        {
           Snackbar.make(view,"Pregnancy date must be before today",Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else {
            if(dates>270)
            {
                Snackbar.make(view,"Pregnancy date can't be older than 9 month from today",Snackbar.LENGTH_SHORT).show();
                return false;
            }
            else return true;
        }
    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();




        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(RegistrationActivity.this);
            pDialog.setMessage("Creating Account");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put("username", userName.getText().toString());
                params.put("email", userEmail.getText().toString());
                params.put("password",userPassword.getText().toString());
                params.put("contact",userPhoneNumber.getText().toString());
                params.put("pregnancydate",userPregnancyDate.getText().toString());
                params.put("type",userType);


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

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {

                try {
                    success = json.getInt(TAG_SUCCESS);
                    message = json.getString(TAG_MESSAGE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {

                finish();
                if(userType.equalsIgnoreCase("P"))
                {

                    NotificationHandler notificationHandler=new NotificationHandler(getApplicationContext());


                    Calendar calDayOfPregnancy = Calendar.getInstance();
                    String[]dates=finalDate.split("/");
                    calDayOfPregnancy.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dates[0]));
                    calDayOfPregnancy.set(Calendar.MONTH,Integer.parseInt(dates[1]));
                    calDayOfPregnancy.set(Calendar.YEAR,Integer.parseInt(dates[2]));



                    notificationHandler.removeNotifications();
                    notificationHandler.setNotifications(calDayOfPregnancy);


                    startActivity(new Intent(RegistrationActivity.this,TimelineActivityPatient.class));
                }

                else if(userType.equalsIgnoreCase("D"))
                {
//                    startActivity(new Intent(RegistrationActivity.this,Do.class));
                }
            }else{
                Snackbar.make(submitButton,"Something went wrong,registration failed",Snackbar.LENGTH_SHORT).show();
            }

        }

    }



}