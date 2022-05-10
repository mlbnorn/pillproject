package com.example.pillproject;
//import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment
{

    public HomeFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View HomeView= inflater.inflate(R.layout.fragment_home, container, false);
        //assignElements();
        //setListeners();
        return HomeView;
    }}
/*
    protected void assignElements(View thisView)
    {
        checkPillsButton = (Button) thisView.findViewById(R.id.button_check_pills);
        med1EditText = (EditText) thisView.findViewById(R.id.med1);
        med2EditText = (EditText) thisView.findViewById(R.id.med2);
    }

    protected void setListeners()
    {
        checkPillsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("test","yesy");
                String m1text = med1EditText.getText().toString();
                String m2text = med2EditText.getText().toString();
                //check for working listener
                Toast.makeText(getActivity(),m1text+" "+m2text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
 */