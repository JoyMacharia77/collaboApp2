package com.joymacharia.collaboapp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    //calender
    private EditText deadline;
    // Variables to hold the selected date(YEAR,MONTH AND DAY)
    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* 3. Connect the edit text variable you created
         *with the one specified in the layout for receiving the date value */
        //////////////////////////deadline = findViewById(R.id.deadline);
        //4. Connect the EditText variable with an onClickListener
        //deadline.setOnClickListener(this);

        //CHAT
       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
           != PackageManager.PERMISSION_GRANTED)
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_my_tasks, R.id.nav_chat, R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void contactUs(MenuItem item)
    {
        Uri myUri = Uri.parse("tel:0710203040");
        Intent myIntent = new Intent(Intent.ACTION_DIAL, myUri);
        startActivity(myIntent);
    }

    public void aboutUs(MenuItem item)
    {
        Uri myAboutUri = Uri.parse("https://www.bitrix24.com/features/tasks.php?gclid=CjwKCAjw88v3BRBFEiwApwLevcXdSpDRcwjOfI5MYc964PlB-GhqgFY9M8rDgyUkBLGEj3iTj0CsQRoCNV8QAvD_BwE");
        Intent myAboutIntent = new Intent(Intent.ACTION_VIEW, myAboutUri);
        startActivity(myAboutIntent);
    }

    public void share(MenuItem item) 
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        //sendIntent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
        sendIntent.setType("text/plain");
        Intent.createChooser(sendIntent,"Share via");
        startActivity(sendIntent);

    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDeadline(View view)
    {
        //6.2 Declare Calender to get current selected date
        final Calendar c= Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //6.3 Declare a date picker dialogue to pick selected date
        final DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadline.setText(dayOfMonth + "-" + (month+1)+"-"+ year);
            }
        }
                ,mYear,mMonth,mDay);
        //6.5 show the date picker dialog
        datePickerDialog.show();
    }

     */

    /*
    final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //6.4 set the date on the editText variable
                deadline.setText(dayOfMonth + "-" + (month+1)+"-"+ year);
            }
        }
                ,mYear,mMonth,mDay);
        //6.5 show the date picker dialog
        datePickerDialog.show();
    }        */

   /* public void setDeadline(View view)
    {
        final Calendar c= Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //Declare a date picker dialogue to pick selected date
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view1, year, month, dayOfMonth) -> deadline.setText(dayOfMonth + "-" + (month+1)+"-"+ year)
                ,mYear,mMonth,mDay);

        /*final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //6.4 set the date on the editText variable
                deadline.setText(dayOfMonth + "-" + (month+1)+"-"+ year);
            }
        }
                ,mYear,mMonth,mDay);
        //6.5 show the date picker dialog
        datePickerDialog.show();
    }  */


    /*public void joinChat(View view)
    {
        setContentView(R.layout.activity_message);
    }*/


}
