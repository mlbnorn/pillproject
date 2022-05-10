package com.example.pillproject;

import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;                   // create new fragment manager
    //RecyclerView rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignElements();


        fragmentManager.beginTransaction()
                .replace(R.id.flFragment,compareFragment)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    HomeFragment homeFragment = new HomeFragment();
    CompareFragment compareFragment= new CompareFragment();
    SearchFragment searchFragment = new SearchFragment();

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);

        return true;
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.home:
                fragmentManager.beginTransaction()
                        .replace(R.id.flFragment,homeFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
                return true;

            case R.id.compare:
                fragmentManager.beginTransaction()
                        .replace(R.id.flFragment,compareFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
                return true;

            case R.id.search:
                Log.i("Navigation", "clicked search");
                fragmentManager.beginTransaction()
                    .replace(R.id.flFragment,searchFragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            return true;
        }
        return false;
    }

    private Object dataqueue()
    {
        ArrayList<ColorSpace.Model> holder=new ArrayList<ColorSpace.Model>();
        return holder;

    }


    public void assignElements()
    {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);               // create bottom navigation
        fragmentManager = getSupportFragmentManager();                      // create new fragment manager
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //rcv = (RecyclerView) findViewById(R.id.recview);

    }


}