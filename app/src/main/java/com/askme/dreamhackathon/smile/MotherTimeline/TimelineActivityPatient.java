package com.askme.dreamhackathon.smile.MotherTimeline;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askme.dreamhackathon.smile.MyMenuFragment;
import com.askme.dreamhackathon.smile.NearbyHospitals;
import com.askme.dreamhackathon.smile.R;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivityPatient extends AppCompatActivity {

    Button trimester1,trimester2,trimester3;
    TextView trimester;
    Button more_options;
    MyMenuFragment mMenuFragment;
    LeftDrawerLayout mLeftDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);

        more_options = (Button)findViewById(R.id.go_to_more_options);


        final RelativeLayout see_whats_happening = (RelativeLayout) findViewById(R.id.week_details);


        trimester1 = (Button) findViewById(R.id.trimester1);
        trimester2 = (Button) findViewById(R.id.trimester2);
        trimester3 = (Button) findViewById(R.id.trimester3);

        trimester = (TextView)findViewById(R.id.trimester);

        trimester1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimester1.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
                trimester2.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                trimester3.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                trimester1.setText("1");
                trimester2.setText("");
                trimester3.setText("");
                trimester.setText("1st\nTrimester");
            }
        });



        trimester2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimester2.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
                trimester1.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                trimester3.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                trimester2.setText("2");
                trimester1.setText("");
                trimester3.setText("");
                trimester.setText("2nd\nTrimester");
            }
        });


        trimester3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimester3.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
                trimester1.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                trimester2.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                trimester3.setText("3");
                trimester2.setText("");
                trimester1.setText("");
                trimester.setText("3rd\nTrimester");
            }
        });



        more_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimelineActivityPatient.this,TimelineDetailsPatient.class);
                startActivity(intent);
            }
        });


        if (see_whats_happening != null) {
            see_whats_happening.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(TimelineActivityPatient.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.see_whats_happening_dialog);

                    ImageView see_whats_happening_image = (ImageView) dialog.findViewById(R.id.see_whats_happening_image);
                    TextView see_whats_happening_details = (TextView) dialog.findViewById(R.id.see_whats_happening_details);

              //      if(week1){

                    see_whats_happening_image.setImageResource(R.drawable.week1);
                    see_whats_happening_details.setText("You’re not really pregnant yet; the clock starts ticking from the first day of your last period. So even though pregnancies are said to be 40 weeks long, you actually carry your baby for only 38 weeks or so. ");

                    //    }

                    /*

                    week2
                    see_whats_happening_image.setImageResource(R.drawable.week2);
                    see_whats_happening_details.setText("Ovulation occurs; ideally, sperm will already be lying (er, swimming) in wait ");

                    week3
                    see_whats_happening_image.setImageResource(R.drawable.week3);
                    see_whats_happening_details.setText("Fertilization occurs in one of the fallopian tubes. Cell division begins at breakneck speed. ");

                    week4
                    see_whats_happening_image.setImageResource(R.drawable.week4);
                    see_whats_happening_details.setText("The fertilized egg (known as a zygote) implants in the wall of the uterus; the placenta and umbilical cord begin to form.");


                    week5
                    see_whats_happening_image.setImageResource(R.drawable.week5);
                    see_whats_happening_details.setText("The gastrointestinal tract, spinal cord, heart, brain, blood and blood vessels begin to form. The embryo is 1∕16 to 1∕8 inch long “crown to rump” (the measurement that’s used until week 13).");


                    week6
                    see_whats_happening_image.setImageResource(R.drawable.week6);
                    see_whats_happening_details.setText("The heart begins to pump blood, and the neural tube that will become the spine closes (which is why taking folic acid early is essential).The embryo takes on a C-shape; arm and leg buds begin to form; and the skin is translucent. Length: about 1∕4 inch. Fetus fact: The heart will beat 54 million times before birth!");

                    week7
                    see_whats_happening_image.setImageResource(R.drawable.week7);
                    see_whats_happening_details.setText("The head is about 1∕3 the size of the entire embryo. The brain and face are developing rapidly, and nostrils and lenses of the eyes begin to form. Arm buds become paddle-shaped; hands begin to form. Length: about 1∕3 inch.");


                    week8
                     see_whats_happening_image.setImageResource(R.drawable.week8);
                    see_whats_happening_details.setText("Brainwave activity starts. Fingers and toes begin to form and are webbed. Lungs, ears, eyes, upper lip and nose start to form. The body is beginning to straighten, and subtle movements begin. Length: about 1∕2 inch. ");

                    week9
                    see_whats_happening_image.setImageResource(R.drawable.week9);
                    see_whats_happening_details.setText("The heart is almost completely developed. Eyelids are forming, as are hair follicles and nipples; the embryo can hiccup now. Fingers and toes are no longer webbed. The arms develop bones, and the hands begin to touch the face. The legs start to move. Length: about 3∕4 inch.");


                    week10
                    see_whats_happening_image.setImageResource(R.drawable.week10);
                    see_whats_happening_details.setText("The developing baby is now called a fetus. The eyelids begin to fuse to protect the eyes. The fetus begins doing occasional breathing movements, although it gets oxygen through the umbilical cord. The skin becomes less translucent, and genitals begin to form. Length: almost 1 ¼ inches.");


                    week11
                    see_whats_happening_image.setImageResource(R.drawable.week11);
                    see_whats_happening_details.setText("Nearly all the organs and body structures are formed and beginning to function. Genitals begin to take on either male or female form. The head makes up about half of the fetus’s body. Length: about 2 inches. Fetus fact: The fetus can sigh, stretch, move its head and suck its thumb. ");

                    week12
                    see_whats_happening_image.setImageResource(R.drawable.week12);
                    see_whats_happening_details.setText("The face begins to look more human. Length: 3 inches (head to heel, the measurement from now on).");


                    week13
                    see_whats_happening_image.setImageResource(R.drawable.week13);
                    see_whats_happening_details.setText("The nose and lips are completely formed, and the fetus begins to produce and excrete urine.");



                    week14
                    see_whats_happening_image.setImageResource(R.drawable.week14);
                    see_whats_happening_details.setText("The nose, lips and taste buds are formed. The head is covered by a fine, soft hair called lanugo. Length: about 5 inches; weight: about 2 ounces. Fetus fact: Starting now, female fetuses show mouth movements much more often than males.");


                    week15
                    see_whats_happening_image.setImageResource(R.drawable.week15);
                    see_whats_happening_details.setText("Your baby is about 4 to 4 1/2 inches and about 1 3/4 of an ounce. If you could see your baby's face, you might be able to see her wince and grimace, because her facial muscles are developing and flexing. All of her tiny organs, nerves, and muscles are starting to function. The intestines have moved farther into the baby's body; her liver begins to secrete bile, which will later aid in the digestion of fats; and her pancreas begins to produce insulin, a hormone which turns sugar into energy. ");


                    week16
                    see_whats_happening_image.setImageResource(R.drawable.week16);
                    see_whats_happening_details.setText("Your baby weighs about 2.8 ounces (79 grams) and is about 4 1/2 inches from crown to rump—roughly the size of a small gerbil. At any time, you will begin to feel fetal movement as your baby's bones harden, and she starts a big growth spurt. Your baby has plenty of room: At this point, she could fit in the palm of your hand. This is a great time to be a fetus. ");



                    week17
                    see_whats_happening_image.setImageResource(R.drawable.week17);
                    see_whats_happening_details.setText("Your baby is about as wide as your palm, about six inches tall, and weighs about four ounces—about as much as a bar of soap. She now weighs more than your placenta. Your baby is now covered with a downy layer of lanugo, which swirls in fingerprint-like formation over her whole body. Her skin is still thin. Brown fat, a special type of fat that plays a role in body heat generation, is being deposited. ");


                    week18
                    see_whats_happening_image.setImageResource(R.drawable.week18);
                    see_whats_happening_details.setText("Fat is being deposited throughout the body. Teeth have started to form. The fetus begins to hear sounds in your body (such as your heart beating) and may even startle at loud noises. Length: about 8 inches; weight: 6 ounces ");


                    week19
                    see_whats_happening_image.setImageResource(R.drawable.week19);
                    see_whats_happening_details.setText("Your baby weighs about 8 1/2 ounces, and measures about 6 inches long.If the baby is a girl, early ovaries contain follicles with forming eggs. Soon, half of the genetic material for your potential future grandchildren will be formed. Pictures of babies at this age show them touching the membrane of the amniotic sac, touching their own faces, reaching for the umbilical cord, pedaling their legs, and sucking their thumbs. ");


                    week20
                    see_whats_happening_image.setImageResource(R.drawable.week20);
                    see_whats_happening_details.setText("A white, creamy substance called vernix caseosa protects the skin from its aqueous environment. Sweat glands form. Length: about 10 inches; weight: 9 ounces. Fetus fact: Starting now, immunities are being transferred from you to the fetus.");


                    week21
                    see_whats_happening_image.setImageResource(R.drawable.week21);
                    see_whats_happening_details.setText("Your baby now weighs about three-quarters of a pound and is approximately 10 1/2 inches long.");


                    week22
                    see_whats_happening_image.setImageResource(R.drawable.week22);
                    see_whats_happening_details.setText("The brain has entered a phase of extremely rapid growth. Length: about 11 inches; weight: just under 1 pound.");


                    week23
                    see_whats_happening_image.setImageResource(R.drawable.week23);
                    see_whats_happening_details.setText("The testicles begin to descend into the groin from the abdomen; the uterus and ovaries have developed. Body proportions are similar to a newborn’s, though the fetus is still thin. The eyes are formed but lack pigmentation. Length: about 11 1∕2 inches; weight: about 1 pound.");


                    week24
                    see_whats_happening_image.setImageResource(R.drawable.week24);
                    see_whats_happening_details.setText("The fetus develops waking/sleeping patterns. Real hair (not lanugo) begins to grow on the head. Length: about 12 inches; weight: 1 ¼ pounds. Fetus fact: If born now, your baby would have about a 50 percent chance of surviving. ");


                    week25
                    see_whats_happening_image.setImageResource(R.drawable.week25);
                    see_whats_happening_details.setText("Your baby weighs 1 1/2 pounds and is a little more than 13 inches long, about the size of a small bag of sugar. In the last third of pregnancy, she'll double and triple her weight.");


                    week26
                    see_whats_happening_image.setImageResource(R.drawable.week26);
                    see_whats_happening_details.setText("The eyelids separate and the eyes are starting to open. Lungs are beginning to develop surfactant, which allows them to inflate. The fetus begins to sleep for longer periods, often when you do. Length: 14 inches; weight: almost 2 pounds. ");

                    week27
                    see_whats_happening_image.setImageResource(R.drawable.week27);
                    see_whats_happening_details.setText("Your baby weighs about two pounds and is about 14 to 15 inches long, about the size of a small pot roast. ");


                    week28
                    see_whats_happening_image.setImageResource(R.drawable.week28);
                    see_whats_happening_details.setText("The fetus can taste and smell, and the eyes can produce tears. The bones are almost fully developed though still soft. Weight gain is rapid from now on. Length: about 15 inches; weight: more than 2 1∕2 pounds. Fetus fact: The brain will increase 400 percent to 500 percent in weight between now and delivery. ");


                    week29
                    see_whats_happening_image.setImageResource(R.drawable.week29);
                    see_whats_happening_details.setText("Your baby is about two and a half pounds and would be between fifteen and seventeen inches tall if she could stand.Your baby's adrenal glands are producing a chemical which will be made into estriol (a form of estrogen) by the placenta. This estriol is thought to stimulate the production of prolactin by your body, and the prolactin makes you produce milk. So even if your baby comes early, you'll still be able to breastfeed.");

                    week30
                    see_whats_happening_image.setImageResource(R.drawable.week30);
                    see_whats_happening_details.setText("Your baby's length is about 16 inches—about as long as a laptop computer—and she measures almost 11 inches from crown to rump. She weighs approximately 2 1/2 to 3 pounds. ");


                    week31
                    see_whats_happening_image.setImageResource(R.drawable.week31);
                    see_whats_happening_details.setText("Your baby weighs between 2 1/2 and 3 1/2 pounds. She continues to gain weight at a faster pace than she lengthens, which will give her those cute chubby cheeks. She's about fourteen to sixteen inches tall, although individual growth rates vary. ");


                    week32
                    see_whats_happening_image.setImageResource(R.drawable.week32);
                    see_whats_happening_details.setText("The fetus practices breathing motions in preparation for birth. All five senses are developed, and REM (dream-cycle) sleep is beginning. Lanugo begins to disappear. Length: about 17 inches; weight: about 4 pounds. Fetus fact: If your baby were born now, he would have an excellent chance of surviving without life-threatening complications");


                    week33
                    see_whats_happening_image.setImageResource(R.drawable.week33);
                    see_whats_happening_details.setText("Your baby's crown-to-rump length is about 17 inches. She weighs about 4 1/2 pounds and gains about eight ounces every week. ");

                    week34
                    see_whats_happening_image.setImageResource(R.drawable.week34);
                    see_whats_happening_details.setText("The fetus is taking deep breaths. The eyes can blink and are open when it’s awake and closed when asleep, and the pupils dilate and constrict in reaction to light. Length: about 18 inches; weight: about 5 pounds. ");

                    week35
                    see_whats_happening_image.setImageResource(R.drawable.week35);
                    see_whats_happening_details.setText("At more than five pounds and between sixteen and twenty inches, your baby is becoming more ready for birth with every passing hour. She's the size of a small roasting chicken.Her nervous system and immune system are still maturing, and she's adding the fat that she'll need to regulate her body temperature. But, everything else, from her toenails to the hair on her head, is fully formed. If she were born now, she'd have more than a ninety-nine percent chance of surviving. ");

                    week36
                    see_whats_happening_image.setImageResource(R.drawable.week36);
                    see_whats_happening_details.setText("With one month to go, your baby weighs about six pounds and is fattening. Her full length from crown to feet is about 20 1/2 inches.");

                    week37
                    see_whats_happening_image.setImageResource(R.drawable.week37);
                    see_whats_happening_details.setText("Your baby is now 20-21 inches or so and weighs about 6-7 pounds; he looks very much like a newborn. In the vast majority of pregnancies, the fetus begins to move into delivery position. ");


                    week38
                    see_whats_happening_image.setImageResource(R.drawable.week38);
                    see_whats_happening_details.setText("The average newborn has a length of 21 1/2 inches and weighs 7 1/2 pounds. She is fully developed, though still adding connections between neurons in the brain (this continues well after birth). Her nails have been growing and now reach to the ends of her fingers and toes. Her movements are quite restricted by her close quarters.");



                    week39
                    see_whats_happening_image.setImageResource(R.drawable.week39);
                    see_whats_happening_details.setText("You're in the home stretch! After nine months of growth and development, your baby is ready to be born, or nearly so. At week 39, your baby is fully developed and anywhere from 17-23 inches long and weighs 6-10 pounds. Don't be frightened if your OB-GYN says your baby is large: It's extremely difficult to judge a baby's weight accurately from the outside. ");



                    week40
                    see_whats_happening_image.setImageResource(R.drawable.week40);
                    see_whats_happening_details.setText("Congratulations - your baby is now fully formed and ready to be born.");

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


        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        week_no_adapter adapter = new week_no_adapter(createlist(39));
        rv.setAdapter(adapter);

        setupToolbar();

        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);

        FragmentManager fm = getSupportFragmentManager();
        mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);

        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment()).commit();
        }

        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);

        mMenuFragment.leftDrawerLayout=mLeftDrawerLayout;


    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
                mMenuFragment.vNavigation.setNavigationItemSelectedListener(navlistner);
            }
        });
    }

    private List<Adapter_details> createlist(int size) {

        List<Adapter_details> result = new ArrayList<Adapter_details>();

        String[] week = {"1","2","3","4","5","6","7","8","9","10"
                        ,"11","12","13","14","15","16","17","18","19","20","21"
                        ,"22","23","24","25","26","27","28","29","30","31","32"
                        ,"33","34","35","36","37","38","39","40","Birth"};

        for(int i=0; i<= size ; i++){
            Adapter_details ad = new Adapter_details();
            ad.week_no = week[i];
            result.add(ad);
        }
        return result;
    }
    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
    NavigationView.OnNavigationItemSelectedListener navlistner=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int id = menuItem.getItemId();
            Intent i;
            //noinspection SimplifiableIfStatement
            mLeftDrawerLayout.closeDrawer();
            switch (id)
            {
                case R.id.menu_timeline:
/*                    i = new Intent(MainActivity.this, nomaratmanfi.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_dailyTips:
/*                    i = new Intent(MainActivity.this, Setting.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_healthChart:
/*                    i = new Intent(MainActivity.this, Payment.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_nearbyHospital:
                    startActivity(new Intent(TimelineActivityPatient.this, NearbyHospitals.class));
                    break;
                case R.id.menu_settings:
/*                    i = new Intent(MainActivity.this, Contactus.class);
                    startActivity(i);*/
                    break;
                case R.id.menu_about:
/*
                    i = new Intent(MainActivity.this, Contactus.class);
                    startActivity(i);
*/
                    break;
            }

            return false;
        }
    };
}