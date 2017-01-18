package com.askme.dreamhackathon.smile.MotherTimeline;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askme.dreamhackathon.smile.R;


public class TimelineDetailsPatient extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline__details);

        final RelativeLayout weight;
        RelativeLayout happening_to_mother;
        RelativeLayout must_to_do;
        final RelativeLayout food,exercise,vaccine,danger;
            RelativeLayout emergency_numbers;

        ImageButton edit_button,edit_button_mother_details;

            final TextView show_weight;

        must_to_do = (RelativeLayout) findViewById(R.id.must_to_do);
        food = (RelativeLayout) findViewById(R.id.Food_Dtails);
        exercise = (RelativeLayout) findViewById(R.id.Exercise);
        vaccine = (RelativeLayout) findViewById(R.id.Vaccines);
        danger = (RelativeLayout) findViewById(R.id.Dangers);
            emergency_numbers = (RelativeLayout) findViewById(R.id.Emergency_numbers);

        edit_button = (ImageButton) findViewById(R.id.edit);
            edit_button_mother_details = (ImageButton) findViewById(R.id.edit_mother_details);


            show_weight = (TextView) findViewById(R.id.weight_view);


            if (edit_button != null) {
                edit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        final Dialog dialog = new Dialog(TimelineDetailsPatient.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.weight_dialog);
                        dialog.show();

                        final EditText get_weight = (EditText) dialog.findViewById(R.id.get_weight);
                        Button ok_button = (Button) dialog.findViewById(R.id.weight_ok_button);

                        ok_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String w = get_weight.getText().toString();
                                show_weight.setText(w.toString());
                                dialog.dismiss();
                            }
                        });
                    }

            });
            }

            if (edit_button_mother_details != null) {
                edit_button_mother_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(TimelineDetailsPatient.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.mother_details_dialog);
                        dialog.show();
                    }
                });
            }


            if (must_to_do != null) {
                must_to_do.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            }


            if (food != null) {
            food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(TimelineDetailsPatient.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.food_dialog);

                    ImageView food_dialog = (ImageView) dialog.findViewById(R.id.food_dialog_image);

                   // food_dialog.setImageResource(R.drawable.first_trimester_nutrition);

                    ////////////////////////////////from database

               //     if(){

                        food_dialog.setImageResource(R.drawable.first_trimester_nutrition);
                  //  }
                    /*
                    else if(){
                        food_dialog.setImageResource(R.drawable.second_trimester_nutrition);
                    }

                    else if(){
                        food_dialog.setImageResource(R.drawable.third_trimester_nutrition);
                    }

                    */

                    dialog.show();

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = dialog.getWindow();
                    lp.copyFrom(window.getAttributes());
    //This makes the dialog take up the full width
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                    window.setAttributes(lp);
                }
            });
        }

        if (exercise != null) {
            exercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(TimelineDetailsPatient.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.exercise_dialog);

                    dialog.show();

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = dialog.getWindow();
                    lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                    window.setAttributes(lp);
                }
            });
        }

        vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if (danger != null) {
            danger.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(TimelineDetailsPatient.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.danger_dialog);
                    dialog.show();

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = dialog.getWindow();
                    lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                    window.setAttributes(lp);
                }
            });
        }

            if (emergency_numbers != null) {
                emergency_numbers.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Dialog dialog = new Dialog(TimelineDetailsPatient.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.emergency_call_dialog);


                        RelativeLayout emergency_number_layout = (RelativeLayout)dialog.findViewById(R.id.call_layout_dhaka);
                        final TextView call = (TextView)dialog.findViewById(R.id.call_number);

                        emergency_number_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                String phone_no= call.getText().toString().replaceAll("-", "");
                                callIntent.setData(Uri.parse("tel:"+phone_no));
                                try{
                                    startActivity(callIntent);
                                }

                                catch (android.content.ActivityNotFoundException ex){
                                    Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        dialog.show();

                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        Window window = dialog.getWindow();
                        lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                        window.setAttributes(lp);
                    }
                });
            }
    }
}
