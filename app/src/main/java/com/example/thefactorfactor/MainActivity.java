package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.accessibilityservice.AccessibilityButtonController;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int num_entered;
    int i = 0;
    int ii = 0;
    int selecte1;
    int selecte2;
    int selecte3;
    int index;
    int random_nofactor, random_no1, random_no2;

    int attemptTest = 0;

    int generate_val = 0;
    int final_check;
    int val1,val2,val3;

    int score=0,longest_winning_streak = 0;

    List factors = new ArrayList();
    List factors1 = new ArrayList();
    List selection = new ArrayList();

    Vibrator vibe;



    CountDownTimer waitTimer,waitbg;

    int timeup = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences bestscore = this.getSharedPreferences("mybestscore",Context.MODE_PRIVATE);
        longest_winning_streak = bestscore.getInt("bestscore",0);




        TextView best = (TextView) findViewById(R.id.streak_val);

        best.setText(""+longest_winning_streak);








        TextView numberEnteredView = (TextView) findViewById(R.id.above_values_view);
        numberEnteredView.setVisibility(View.GONE);
        numberEnteredView.setText("Select the factor of the entered number . " );

        Button button1 = (Button) findViewById(R.id.random_number_1);
        Button button2 = (Button) findViewById(R.id.random_number_2);
        Button button3 = (Button) findViewById(R.id.random_number_3);
        Button submitbut = (Button) findViewById(R.id.submit);

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        submitbut.setVisibility(View.GONE);



        TextView beststreak = (TextView) findViewById(R.id.streak_val);
        beststreak.setText(""+longest_winning_streak);




    }

    public void generateOptions(View view) {

        try {


            EditText EnteredNum = (EditText) findViewById(R.id.Entered_number_view);
            EnteredNum.onEditorAction(EditorInfo.IME_ACTION_DONE);

            LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);
            mainlayout.setBackgroundColor(getResources().getColor(R.color.yellow));


            if (EnteredNum.length() == 0) {

                Toast toastm = Toast.makeText(this, "Enter a valid input", Toast.LENGTH_SHORT);

                toastm.show();
                EnteredNum.setError("Enter an input no.");
            } else if (Integer.valueOf(EnteredNum.getText().toString()) == 0 || EnteredNum.length() >= 8) {

                Toast toastm = Toast.makeText(this, "Invalid input \n (input must not be equal to 0)\n or \n (no. of digits must not exceed 7)", Toast.LENGTH_SHORT);

                toastm.show();
            } else {

                EditText inputText = (EditText) findViewById(R.id.Entered_number_view);
                inputText.setVisibility(View.GONE);
                Button generatebutton = (Button) findViewById(R.id.generate);
                generatebutton.setVisibility(View.GONE);

                TextView Viewaboveeditview = (TextView) findViewById(R.id.tt);
                Viewaboveeditview.setVisibility(View.GONE);
                TextView Viewbeloweditview = (TextView) findViewById(R.id.ttt);
                Viewbeloweditview.setVisibility(View.GONE);

                TextView numberEnteredView = (TextView) findViewById(R.id.above_values_view);
                numberEnteredView.setVisibility(View.VISIBLE);
                numberEnteredView.setText("Select the factor of the \n"+" entered number : " + EnteredNum.getText().toString() +"\n  and hit Submit button" );


                if (generate_val == 0) {
                    generate_val = 1;

                    num_entered = Integer.valueOf(EnteredNum.getText().toString());


                    if (num_entered < 0) {
                        num_entered = Math.abs(num_entered);

                    }

                    Button no1 = (Button) findViewById(R.id.random_number_1);
                    Button no2 = (Button) findViewById(R.id.random_number_2);
                    Button no3 = (Button) findViewById(R.id.random_number_3);

                    generateFactor(num_entered);
                    generateRandomNos2(num_entered + 20);

                    Random rand2 = new Random();

                    selection.add(1);
                    selection.add(2);
                    selection.add(3);


                    selecte1 = Integer.valueOf(selection.get(rand2.nextInt(selection.size())).toString());
                    int y = 0;
                    while (true) {
                        if (selecte1 == Integer.valueOf(selection.get(y).toString())) {
                            index = y;
                            break;
                        }
                        y += 1;

                    }
                    selection.remove(index);
                    selecte2 = Integer.valueOf(selection.get(rand2.nextInt(selection.size())).toString());
                    y = 0;
                    while (true) {
                        if (selecte2 == Integer.valueOf(selection.get(y).toString())) {
                            index = y;
                            break;
                        }
                        y += 1;

                    }
                    selection.remove(index);
                    selecte3 = Integer.valueOf(selection.get(rand2.nextInt(selection.size())).toString());

                    if (selecte1 == 1) {
                        val1 = random_nofactor;

                        //no1.setText(factors1.toString() + " " + random_nofactor);
                        no1.setText(" " + random_nofactor);

                    }
                    if (selecte1 == 2) {
                        val2 = random_nofactor;

                        // no2.setText(factors1.toString() + " " + random_nofactor);
                        no2.setText(" " + random_nofactor);
                    }
                    if (selecte1 == 3) {
                        val3 = random_nofactor;

                        //no3.setText(factors1.toString() + " " + random_nofactor);
                        no3.setText(" " + random_nofactor);
                    }

                    if (selecte2 == 1) {
                        val1 = random_no1;
                        no1.setText(String.valueOf(random_no1));
                    }
                    if (selecte2 == 2) {
                        val2 = random_no1;
                        no2.setText(String.valueOf(random_no1));
                    }
                    if (selecte2 == 3) {
                        val3 = random_no1;
                        no3.setText(String.valueOf(random_no1));
                    }

                    if (selecte3 == 1) {
                        val1 = random_no2;
                        no1.setText(String.valueOf(random_no2));
                    }
                    if (selecte3 == 2) {
                        val2 = random_no2;
                        no2.setText(String.valueOf(random_no2));
                    }
                    if (selecte3 == 3) {
                        val3 = random_no2;
                        no3.setText(String.valueOf(random_no2));
                    }

//                    TextView select = (TextView) findViewById(R.id.tt);
//                    select.setText(" " + selecte1 + selecte2 + selecte3);
                    selection.clear();


//        no1.setText(factors1.toString() + " " + random_nofactor);
//        no2.setText(String.valueOf(random_no1));
//        no3.setText(String .valueOf(random_no2));


                    factors.clear();
                    factors1.clear();
                    i = 0;

                    Button button1 = (Button) findViewById(R.id.random_number_1);
                    Button button2 = (Button) findViewById(R.id.random_number_2);
                    Button button3 = (Button) findViewById(R.id.random_number_3);
                    Button submitbut = (Button) findViewById(R.id.submit);

                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    submitbut.setVisibility(View.VISIBLE);

                    button1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    button2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    button3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                    waitTimer = new CountDownTimer(10000, 1000) {

                        TextView timer = (TextView) findViewById(R.id.timer);
                        TextView timercolor = (TextView) findViewById(R.id.timer);

                        @Override
                        public void onTick(long millisUntilFinished) {


                            timercolor.setBackgroundColor(getResources().getColor(R.color.yellow));
                            timer.setText("" + millisUntilFinished / 1000);
                            if (millisUntilFinished / 1000 <= 3){
                                timercolor.setBackgroundColor(getResources().getColor(R.color.red));
                                if (millisUntilFinished / 1000 == 3){
                                    Toast toastms = Toast.makeText(MainActivity.this,"Hurry Up! 3 secs left!",Toast.LENGTH_SHORT);
                                    toastms.show();
                                }
                            }


                        }

                        @Override
                        public void onFinish() {

                            timer.setText("10");

                            timercolor.setBackgroundColor(getResources().getColor(R.color.yellow));

                            Button button1 = (Button) findViewById(R.id.random_number_1);
                            Button button2 = (Button) findViewById(R.id.random_number_2);
                            Button button3 = (Button) findViewById(R.id.random_number_3);
                            Button submitbut = (Button) findViewById(R.id.submit);

                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            submitbut.setVisibility(View.GONE);

                            Button no1 = (Button) findViewById(R.id.random_number_1);
                            Button no2 = (Button) findViewById(R.id.random_number_2);
                            Button no3 = (Button) findViewById(R.id.random_number_3);

                            no1.setText("");
                            no2.setText("");
                            no3.setText("");

                            EditText textedit = (EditText) findViewById(R.id.Entered_number_view);
                            textedit.setText("");

                            Toast toastkk = Toast.makeText(MainActivity.this, "Time up! \n \n  "+"Total Score : "+score+"!"+"\n \n Try again", Toast.LENGTH_SHORT);

                            toastkk.show();

                            waitbg = new CountDownTimer(1000, 1) {


                                LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);

                                @Override
                                public void onTick(long millisUntilFinished) {
                                    LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);
                                    mainlayout.setBackgroundColor(getResources().getColor(R.color.red));

                                }

                                @Override
                                public void onFinish() {

                                    mainlayout.setBackgroundColor(getResources().getColor(R.color.yellow));


                                }
                            }.start();

                            vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                            vibe.vibrate(1000 );

                            score=0;


                            TextView scoreval = (TextView) findViewById(R.id.score_val);


                            scoreval.setText("" + score);

                            EditText inputText = (EditText) findViewById(R.id.Entered_number_view);
                            inputText.setVisibility(View.VISIBLE);
                            Button generatebutton = (Button) findViewById(R.id.generate);
                            generatebutton.setVisibility(View.VISIBLE);

                            TextView Viewaboveeditview = (TextView) findViewById(R.id.tt);
                            Viewaboveeditview.setVisibility(View.VISIBLE);
                            TextView Viewbeloweditview = (TextView) findViewById(R.id.ttt);
                            Viewbeloweditview.setVisibility(View.VISIBLE);

                            TextView numberEnteredView = (TextView) findViewById(R.id.above_values_view);
                            numberEnteredView.setVisibility(View.GONE);
                            numberEnteredView.setText("Select the factor of the entered number . "  );



                            generate_val = 0;


                        }
                    }.start();


                } else {
                    Toast toast = Toast.makeText(this, "Options can be generated only once", Toast.LENGTH_SHORT);

                    toast.show();


                }


            }
        }
        catch (Exception e){
            Toast toast = Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT);

            toast.show();
        }
    }

    private void generateFactor(int number) {


        while (i <= number) {
            i += 1;
            if (number % i == 0) {

                factors.add(i);
                factors1.add(i);
            }
        }
        for (int t = 0;t<factors.size();t+=1){
            factors1.add(-Integer.valueOf(factors.get(t).toString()));

        }
        Random rand = new Random();
        random_nofactor = Integer.valueOf(factors1.get(rand.nextInt(factors1.size())).toString());

    }

    private void generateRandomNos2(int number) {

        Random rand1 = new Random();

        random_no1 = rand1.nextInt(number) -  10;
        random_no2 = rand1.nextInt(number) - 10;

        randomNo1Check(num_entered);
        randomNo2Check(num_entered);


    }



        private void randomNo1Check(int num){

            Random rand1 = new Random();
            while (true) {

                int j = 0;

                while (j < factors1.size()) {
                    if ((random_no1 == Integer.valueOf(factors1.get(j).toString())) || (random_no1 == random_no2)) {

                        random_no1 = rand1.nextInt(num_entered+20);
                        break;
                    }

                    j += 1;

                }
                if (j>=factors1.size()){
                    break;
                }
            }
        }

    private void randomNo2Check(int num){

        Random rand1 = new Random();
        while (true) {

            int j = 0;

            while (j < factors1.size()) {
                if ((random_no2 == Integer.valueOf(factors1.get(j).toString())) || (random_no1 == random_no2)) {

                    random_no2 = rand1.nextInt(num_entered+20);
                    break;
                }

                j += 1;

            }
            if (j>=factors1.size()){
                break;
            }
        }
    }
    public void button1(View view){

        attemptTest = 1;

        Button button1 = (Button) findViewById(R.id.random_number_1);
        Button button2 = (Button) findViewById(R.id.random_number_2);
        Button button3 = (Button) findViewById(R.id.random_number_3);

        button1.setBackgroundColor(getResources().getColor(R.color.green));
        button2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        button3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        final_check = val1;


    }

    public void button2(View view){

        attemptTest = 1;

        Button button1 = (Button) findViewById(R.id.random_number_1);
        Button button2 = (Button) findViewById(R.id.random_number_2);
        Button button3 = (Button) findViewById(R.id.random_number_3);

        button2.setBackgroundColor(getResources().getColor(R.color.green));
        button1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        button3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        final_check = val2;



    }
    public void button3(View view){

        attemptTest = 1;

        Button button1 = (Button) findViewById(R.id.random_number_1);
        Button button2 = (Button) findViewById(R.id.random_number_2);
        Button button3 = (Button) findViewById(R.id.random_number_3);

        button1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        button2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        button3.setBackgroundColor(getResources().getColor(R.color.green));

        final_check = val3;


    }
    public void submit(View view) {



            if (attemptTest == 1) {

                TextView timercolor = (TextView) findViewById(R.id.timer);
                timercolor.setBackgroundColor(getResources().getColor(R.color.yellow));

                Button button1 = (Button) findViewById(R.id.random_number_1);
                Button button2 = (Button) findViewById(R.id.random_number_2);
                Button button3 = (Button) findViewById(R.id.random_number_3);
                Button submitbut = (Button) findViewById(R.id.submit);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                submitbut.setVisibility(View.GONE);

                Button no1 = (Button) findViewById(R.id.random_number_1);
                Button no2 = (Button) findViewById(R.id.random_number_2);
                Button no3 = (Button) findViewById(R.id.random_number_3);

                no1.setText("");
                no2.setText("");
                no3.setText("");

                if (final_check == random_nofactor) {


                    score += 10;
                    if (longest_winning_streak < score){
                        longest_winning_streak = score;
                    }
                    attemptTest = 0;

                    Toast toast = Toast.makeText(this, " Wow! correct answer", Toast.LENGTH_SHORT);

                    toast.show();

                    waitbg = new CountDownTimer(1000, 1) {


                        LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);

                        @Override
                        public void onTick(long millisUntilFinished) {
                            LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);
                            mainlayout.setBackgroundColor(getResources().getColor(R.color.green));

                        }

                        @Override
                        public void onFinish() {

                            mainlayout.setBackgroundColor(getResources().getColor(R.color.yellow));


                        }
                    }.start();

                }
                if ((final_check == random_no1) || (final_check == random_no2)) {


                    attemptTest = 0;


                    Toast toast = Toast.makeText(this, " No! wrong answer \n" + "The correct answer is " + random_nofactor+"\n \n  "+"Total Score : "+score+"!"+"\n \n Try again", Toast.LENGTH_SHORT) ;

                    toast.show();
                    score = 0;

                    waitbg = new CountDownTimer(1000, 1) {


                        LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);

                        @Override
                        public void onTick(long millisUntilFinished) {
                            LinearLayout mainlayout = (LinearLayout) findViewById(R.id.main_layout);
                            mainlayout.setBackgroundColor(getResources().getColor(R.color.red));

                        }

                        @Override
                        public void onFinish() {

                            mainlayout.setBackgroundColor(getResources().getColor(R.color.yellow));


                        }
                    }.start();


                    vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibe.vibrate(1000 );







                }
                EditText textedit = (EditText) findViewById(R.id.Entered_number_view);
                textedit.setText("");

                if (waitTimer != null) {
                    waitTimer.cancel();
                    waitTimer = null;
                    TextView timer = (TextView) findViewById(R.id.timer);
                    timer.setText("" + 10);
                }

                TextView scoreval = (TextView) findViewById(R.id.score_val);
                TextView sterak  = (TextView) findViewById(R.id.streak_val);

                scoreval.setText("" + score);
                sterak.setText(""+longest_winning_streak);

                // saving the longest winning streak

                SharedPreferences bestscore = getSharedPreferences("mybestscore", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = bestscore.edit();
                editor.putInt("bestscore",longest_winning_streak);
                editor.commit();


                EditText inputText = (EditText) findViewById(R.id.Entered_number_view);
                inputText.setVisibility(View.VISIBLE);
                Button generatebutton = (Button) findViewById(R.id.generate);
                generatebutton.setVisibility(View.VISIBLE);
                TextView Viewaboveeditview = (TextView) findViewById(R.id.tt);
                Viewaboveeditview.setVisibility(View.VISIBLE);
                TextView Viewbeloweditview = (TextView) findViewById(R.id.ttt);
                Viewbeloweditview.setVisibility(View.VISIBLE);
                TextView numberEnteredView = (TextView) findViewById(R.id.above_values_view);
                numberEnteredView.setVisibility(View.GONE);
                numberEnteredView.setText("Select the factor of the entered number . " );



                generate_val = 0;

            } else {
                Toast toast = Toast.makeText(this, "no options Selected", Toast.LENGTH_SHORT);

                toast.show();
            }


    }

    }




