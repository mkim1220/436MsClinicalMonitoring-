package com.example.alexandraboukhvalova.a436msclinicalmonitoring;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.example.sheets436.Sheets;

public class TappingActivity extends AppCompatActivity {
    Button instructionsBtn;
    Button startTestBtn;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapping);

        instructionsBtn = (Button)findViewById(R.id.InstructionsButton);
        startTestBtn = (Button)findViewById(R.id.starTestButton);
        addListenerOnButton();

        // add button listener
        instructionsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Tap Test Instructions");

                // set dialog message
                alertDialogBuilder
                        .setMessage("When the countdown completes, use your LEFT hand to tap the box as " +
                                "many times as possible within 10 seconds. After the first round ends, repeat this " +
                                "process with your RIGHT hand.\"")
                        .setCancelable(false)
                        .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    public void addListenerOnButton() {

        final Context context = this;

        startTestBtn = (Button) findViewById(R.id.starTestButton);

        startTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, TimingActivity.class);
                startActivity(intent);

            }
        });

    }



}
