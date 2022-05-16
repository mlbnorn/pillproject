package com.example.pillproject;

import java.io.*;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // Frontend Components
    HomeFragment homeFragment;
    CompareFragment compareFragment;
    SearchFragment searchFragment;
    BottomNavigationView bottomNavigationView;

    // Backend Components
    FragmentManager fragmentManager;
    ArrayList<Drug> dataList;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignElements();
        setCurrentFragment(compareFragment);

        fetchData();
    }

    private void setCurrentFragment(Fragment fragment)
    {
        fragmentManager.beginTransaction()
                .replace(R.id.flFragment,fragment)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        bottomNavigationView.setSelectedItemId(R.id.compare);

        return true;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home: setCurrentFragment(homeFragment); return true;
            case R.id.compare: setCurrentFragment(compareFragment); return true;
            case R.id.search: setCurrentFragment(searchFragment); return true;
        }
        return false;
    }

    public void assignElements()
    {
        homeFragment = new HomeFragment();
        compareFragment= new CompareFragment();
        searchFragment = new SearchFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private void fetchData()
    {
        dbh = new DatabaseHelper(this);
        try {
            dbh.createDataBase();
            dbh.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        dataList = dbh.getDrugs(this);
    }

    public ArrayList<Drug> getDataList() {
        return dataList;
    }
}